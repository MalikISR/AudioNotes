package com.malik_isr.vkau

import android.content.Context
import android.media.MediaRecorder
import android.os.Environment

class AudioRecord(private val context: Context) {

    private var mediaRecorder: MediaRecorder? = null

    fun start(){

        val mediaRecorder = MediaRecorder()
        stop()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder.setAudioEncodingBitRate(128000)
        mediaRecorder.setAudioSamplingRate(60000)
        mediaRecorder.setOutputFile(getPath())
        mediaRecorder.prepare()
        mediaRecorder.start()

        this.mediaRecorder = mediaRecorder
    }

    fun isRecording():Boolean{
        return mediaRecorder != null
    }

    private fun getPath(): String{
        return "${context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)}/${System.currentTimeMillis()}.wav"
    }

    fun getVolume(): Int{
        return mediaRecorder?.maxAmplitude ?: 0
    }

    fun stop(){
        if(mediaRecorder == null) return
        try {
            mediaRecorder?.stop()
            mediaRecorder?.release()

        } catch (_: java.lang.Exception) {}
        mediaRecorder = null
    }
}