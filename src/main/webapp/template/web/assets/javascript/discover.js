if ($('.option-all__playlist-item0') !== null) {
    $('.option-all__playlist-item0').onclick = function () {
        $('.create-playlist').classList.add('show');
        $('#overlay').classList.add('show');
    };
}