package com.rutvi.mad_practical6_21012011123

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {
    companion object{
        val playerconstant="play/false"
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    lateinit var player:MediaPlayer


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(!this::player.isInitialized)
        {
            player=MediaPlayer.create(this,R.raw.song)
        }
        if(intent!=null) {
            val command = intent.getStringExtra(playerconstant)
            if (!player.isPlaying) {
                player.start()
            } else {
                player.pause()
            }
        }
        else
        {
            player.stop()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }
}