package fastcampus.aop.part4.chapter02

data class PlayerModel(
    private val playMusicList: List<MusicModel> = emptyList(),
    var currentPosition: Int = -1,
    var isWatchingPlayListView: Boolean = true,
) {

    // position에 따라 재생 여부를 설정해주기 위한 함수
    fun getAdapterModels(): List<MusicModel> {
        return playMusicList.mapIndexed { index, musicModel ->
            // copy : 원하는 값만 바꿔주고 나머지 값은 그대로 복사해서 새로운 인스턴스를 만들어줌
            val newItem = musicModel.copy(
                isPlaying = index == currentPosition
            )
            newItem
        }
    }

    fun updateCurrentPosition(musicModel: MusicModel) {
        currentPosition = playMusicList.indexOf(musicModel)
    }

    fun getNextMusic(): MusicModel? {
        if (playMusicList.isEmpty()) return null

        currentPosition = (currentPosition + 1) % playMusicList.size

        return playMusicList[currentPosition]
    }

    fun getPrevMusic(): MusicModel? {
        if (playMusicList.isEmpty()) return null

        currentPosition =  if (currentPosition != 0) currentPosition - 1 else playMusicList.lastIndex

        return playMusicList[currentPosition]
    }

    fun currentMusicModel(): MusicModel? {
        if (playMusicList.isEmpty()) return null

        return playMusicList[currentPosition]
    }

}