package com.example.translatorapp.domain.repository

import androidx.camera.view.LifecycleCameraController
import androidx.navigation.NavController

interface CameraRepository {
    suspend fun takePhoto(controller: LifecycleCameraController)
}