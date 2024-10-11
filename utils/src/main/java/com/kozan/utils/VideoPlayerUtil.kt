package com.kozan.utils

/**
 * class VideoPlayerActivity : AppCompatActivity() {
 *
 *     private lateinit var playerView: PlayerView
 *     private lateinit var videoPlayerUtil: VideoPlayerUtil
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(R.layout.activity_video_player)
 *
 *         playerView = findViewById(R.id.player_view)
 *         videoPlayerUtil = VideoPlayerUtil(this, playerView)
 *
 *         // Örnek video kaynağının ID'si (res/raw/video.mp4)
 *         videoPlayerUtil.initializePlayer(R.raw.video) // video.mp4 dosyasının raw klasöründe olduğunu varsayıyoruz
 *     }
 *
 *     override fun onPause() {
 *         super.onPause()
 *         videoPlayerUtil.pausePlayer() // Activity duraklatıldığında videoyu duraklat
 *     }
 *
 *     override fun onResume() {
 *         super.onResume()
 *         if (videoPlayerUtil.isPlaying()) {
 *             videoPlayerUtil.resumePlayer() // Activity devam ettiğinde videoyu devam ettir
 *         }
 *     }
 *
 *     override fun onDestroy() {
 *         super.onDestroy()
 *         videoPlayerUtil.releasePlayer() // Activity yok edildiğinde kaynakları serbest bırak
 *     }
 * }
 *
 * */



/*class VideoPlayerUtil(private val context: Context, private val playerView: PlayerView) {

    private var player: ExoPlayer? = null

    fun initializePlayer(videoResId: Int) {
        player = ExoPlayer.Builder(context).build().apply {
            playerView.player = this
            val videoUri = Uri.parse("android.resource://${context.packageName}/$videoResId")
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true // Oynatmayı hemen başlat
        }

        player?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    player?.seekTo(0)
                    player?.play()
                }
            }
        })
    }

    fun releasePlayer() {
        player?.release()
        player = null
        playerView.player = null // PlayerView referansını temizle
    }

    fun pausePlayer() {
        player?.pause()
    }

    fun resumePlayer() {
        player?.play()
    }

    fun stopPlayer() {
        player?.stop()
    }

    fun isPlaying(): Boolean {
        return player?.isPlaying == true
    }
}*/
