package com.example.translatorapp.data.repository

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import com.example.translatorapp.R
import com.example.translatorapp.domain.repository.CameraRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CameraRepositoryImpl(
    private val context: Context
): CameraRepository {
    override suspend fun takePhoto(controller: LifecycleCameraController) {
        controller.takePicture(
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                @RequiresApi(Build.VERSION_CODES.Q)
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    val matrix = android.graphics.Matrix().apply {
                        postRotate(
                            image.imageInfo.rotationDegrees.toFloat()
                        )
                    }
                    val imageBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )
                    CoroutineScope(Dispatchers.IO).launch{
                        savePhoto(imageBitmap)
                    }
                }
            }
        )
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private suspend fun savePhoto(bitmap: Bitmap){
        withContext(Dispatchers.IO){
            val resolver:ContentResolver = context.contentResolver
            val imageCollection = MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL_PRIMARY
            )
            val appName = context.getString(R.string.app_name)
            val timeInMillis = System.currentTimeMillis()
            val imageContentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "${timeInMillis}_image"+".jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_DCIM+"/$appName")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.DATE_TAKEN, timeInMillis)
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
            val imageMediaStoreUri = resolver.insert(
                imageCollection,
                imageContentValues
            )
            imageMediaStoreUri?.let {uri->
                try {
                    resolver.openOutputStream(uri)?.let { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    }
                    imageContentValues.clear()
                    imageContentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                    resolver.update(uri, imageContentValues, null, null)
                }catch (e:Exception){
                    e.printStackTrace()
                    resolver.delete(uri, null, null)
                }
            }
        }
    }
}