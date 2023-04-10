const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const optionAllSongList = $('.option-all__songs-list');
const musicNowTym = $('.music-control__left-action-tym-box');
const volumeIcon = $('.music-control__right-volume-icon');
const audio = $('#audio');
// var sliderItems=Array.from($$('.option-all__song-slider-img'));
const sliderBox = $('.option-all__song-slider');
const sliderItems = $$('.option-all__song-slider-img');
const playBtn = $('.js__music-control__icon-play');
const thunbPlayerBox = $('.music-control__left');
const progress = $('#progress');
const remainTime = $('.js__music-control__progress-time-start');
const durationTime = $('.js__music-control__progress-time-duration');
const prevBtn = $('.js__music-control__icon2');
const nextBtn = $('.js__music-control__icon4');
const nameSong = $('.music-control__left-content-song');
const nameSinger = $('.music-control__left-content-singer');
const cdThumb = $('.music-control__left-img');
const playAllBtn = $('.js__playall-0');
const playAllBtn1 = $('.js__playall-1');
const randomBtn = $('.js__music-control__icon1');
const repeatBtn = $('.js__music-control__icon5');
const volumeProgress = $('#progress1');
const header = $('.header');
const mainContainer = $('.main-container');
const headerSetting = $('.header__setting');
const headerOverlay = $('.header__right-overlay');
const headerSettingList = $('.header__setting-list');
const tabs = $$('.tabs-item');
const panes = $$('.panes-item');
const sideBarTabs = $$('.js__sidebar-tabs');
const containerPanes = $$('.js__container-panes');
const slidersDiscover = $$('.container-discover__slider-item');
const artist = $$('.artist-js');
const search = $('.header__width-search-input');
const songItems = $$('.js__song-item0');






var backgroundIndex= 0;
// x = 100  // center
// y = 50   // center
// r = 50   // radius
// a = 2    // angle (from 0 to Math.PI * 2)







const app = {
    // sliderIndex: 0,
    songsData : [

    ],
    currentIndex : 0,
    isPlaying: false,
    isRandom: false,
    isRepeat: false,
    isMute: false,
    volume: 100,

    defineProperties: function() {
        Object.defineProperty(this, 'currentSong', {
            get: function() {
                return this.songsData[this.currentIndex];
            }
        })
    },



    toastSlide: function() {
        const toatMain = $('#toast');
        if (toatMain) {
            const toast = document.createElement('div');
            toast.classList.add('toast');
            toast.innerHTML = `
                <div class="toast__item">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    <span>Chức năng này đang được phát triển, bạn vui lòng thử lại sau !</span>
                </div>
            `;
            toatMain.appendChild(toast);
            setTimeout(function() {
                toatMain.removeChild(toast);
            }, 3000)
        }
    },


    // KHI ACTIVE KHUẤT THÌ ĐƯA ITEM ACTIVE LÊN VIEW
    scrollToActiveSong: function () {
        if ($(".songs-item--active") !== null) {
            setTimeout(() => {
                $(".songs-item--active").scrollIntoView({
                    behavior: "smooth",
                    block: "nearest"
                });
            }, 300);
        }
    },

    RandomSong: function () {
        let newIndex;
        do {
          newIndex = Math.floor(Math.random() * this.songsData.length);
        } while (newIndex === this.currentIndex);

        this.currentIndex = newIndex;
        this.loadCurrentSong();
    },

    loadCurrentSong: function () {
        nameSong.textContent = this.currentSong.name;
        nameSinger.textContent = this.currentSong.singer;
        // let url = "<c:url value=''/>";
        // let urlBg = url.substring(0, url.length - 3) + this.currentSong.background  +" '/>  ";
        // console.log(urlBg);
        // 'url(' + this.currentSong.background +')'
        cdThumb.style.backgroundImage = this.currentSong.background;
        this.displayDurationTime();
        // let urlAudio = url.substring(0, url.length - 3) + this.currentSong.pathSong  +" '/>  ";
        // console.log(urlAudio);
        audio.src = this.currentSong.pathSong;
    },

    nextSong: function () {
        this.currentIndex++;
        if (this.currentIndex >= this.songsData.length) {
          this.currentIndex = 0;
        }
        this.loadCurrentSong();
    },

    prevSong: function () {
        this.currentIndex--;
        if (this.currentIndex < 0) {
          this.currentIndex = this.songsData.length - 1;
        }
        this.loadCurrentSong();
    },

    // ĐỊNH DẠNG LẠI THỜI GIAN CHO ĐẸP
    formatTime : function(number) {
        const minutes = Math.floor(number / 60);
        const seconds = Math.floor(number - minutes * 60);
        return `${minutes < 10 ? "0" + minutes : minutes}:${seconds < 10 ? "0" + seconds : seconds}`;
    },

    // HIỂN THỊ REMAIN TIME TIME VÀO PLAYER
    displayRemainTime : function() {
        remainTime.textContent = this.formatTime(audio.currentTime);

    },

    // HIỂN THỊ VÀ DURATION TIME VÀO PLAYER
    displayDurationTime : function() {

        durationTime.textContent = this.currentSong.duration;
    },

    verifyOptionTextColor: function() {
        $$('.music__option-item').forEach((tab, index) => {
            if(backgroundIndex === 0 || backgroundIndex === 1 || backgroundIndex === 2) {
                tab.style.color = '#fff'
            } else {
                tab.style.color = '#000'
            }
        })
    },

    // hiddenTym: function() {
    //     $('.music-control__left-action-tym').style.display = 'none';
    //     $$('.songs-item-right-tym').forEach((item) => {
    //         item.style.display = 'none';
    //     });
    // },

    // SỰ KIỆN VÀ XỬ LÝ
    handleEvents: function () {
        const _this = this;
        const songTyms = $$('.songs-item-right-tym');
        const playBtnIcons = $$('.js__songs-item-left-img-0');


        // this.hiddenTym();
        // chuyển tab option
        tabs.forEach((tab, index) => {
            const pane = panes[index];
            $('.panes-item:not(.music__option-item--active)').style.backgroundColor = 'transparent';
            // themeItems[backgroundIndex].click();
            _this.verifyOptionTextColor();
            tab.onclick = function() {
                $('.music__option-item.music__option-item--active').classList.remove('music__option-item--active');
                tab.classList.add('music__option-item--active')
                $('.panes-item.active').classList.remove('active');
                tabs[0].style.backgroundColor = 'transparent';
                tabs[1].style.backgroundColor = 'transparent';
                tabs[2].style.backgroundColor = 'transparent';
                tab.style.backgroundColor = `var(--option-color-${backgroundIndex})`;
                pane.classList.add('active')
                // if (index === 1) {
                //     _this.renderPlayList1($('.option-music-list'),_this.songsData);
                // }
                $('.music__option-item.music__option-item--active').classList.remove('js__main-color');
            }
        });

        // // khi mới mở web thì sẽ chọn hightlight dòng đầu tiên
        // songItems[this.currentIndex].classList.add('songs-item-playbtn--active');


        songTyms.forEach((songTym, index) => {
            songTym.onclick = function() {
                songTym.classList.toggle('songs-item-right-tym--active');
            }
        });

        // CLICK TYM Ở NOW PLAYER
        // musicNowTym.onclick = function() {
        //     this.classList.toggle('music-control__left-action-tym-box-active');
        // }

        // BẬT TĂT MUTE Ở VOLUME
        volumeIcon.onclick = function() {
            _this.isMute = !_this.isMute;
            volumeIcon.classList.toggle('music-control__right--active', _this.isMute);
            if (_this.isMute) {
                audio.volume = 0;
                volumeProgress.value = 0;
            } else {
                audio.volume = _this.volume / 100;
                volumeProgress.value = _this.volume;
            }
        }

        // TĂNG GIẢM ÂM LƯỢNG
        volumeProgress.onchange = function(e) {
            _this.volume = e.target.value;
            audio.volume = e.target.value / 100;
            if (e.target.value == 0) {
                volumeIcon.classList.add('music-control__right--active')
                _this.isMute = true;
            } else {
                volumeIcon.classList.remove('music-control__right--active');
                _this.isMute = false;
            }
        }


        // XỬ LÝ CD QUAY/DỪNG
        const cdThumbAnimate = cdThumb.animate([{ transform: "rotate(360deg)" }], {
            duration: 10000, // 10 seconds
            iterations: Infinity
        });
        cdThumbAnimate.pause();


        // XỬ LÝ KHI CLICK VÀO NÚT PLAY
        playBtn.onclick = function () {
            if (_this.isPlaying) {
              audio.pause();
            } else {
              audio.play();
            }
        }

        // Khi song được play
        audio.onplay = function () {

            _this.isPlaying = true;
            // player.classList.add("playing");
            cdThumbAnimate.play();
            // cdThumb.style.background-image = "url(" song
            playBtn.classList.add('music-control__icon-play--active');
            thunbPlayerBox.style.transform = "translateX(20px)";

            songItems[_this.currentIndex].classList.add('songs-item-playing--active-onplay');
            songItems[_this.currentIndex].classList.add('songs-item--active');
            songItems[_this.currentIndex].classList.remove('songs-item-playbtn--active');

            let href = $('.songs-item-playing--active-onplay').getElementsByClassName('songs-item-left-body')[0]
                .getElementsByTagName("a")[0].href;
            var songId = href.substring(href.search('=')+1);
            // send request thêm lịch sử

            let send = async function addHistory() {
                try {
                    let url = "http://localhost:8080/WalletMusic_war_exploded/api-user?songId=" + songId;
                    const response = await fetch(url, {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    });

                    const result = await response.json();
                } catch (error) {
                    // window.location.href = "http://localhost:8080/WalletMusic_war_exploded/trang-chu";
                }
            }
            send();
        };

        // KHI SONG BỊ PAUSE
        audio.onpause = function () {
            _this.isPlaying = false;
            cdThumbAnimate.pause();
            playBtn.classList.remove('music-control__icon-play--active');
            thunbPlayerBox.style.transform = "translateX(0)";
            // if (songItems.lenght > 0) {
                songItems[_this.currentIndex].classList.remove('songs-item-playing--active-onplay');
                songItems[_this.currentIndex].classList.add('songs-item-playbtn--active');
            // }
        }

        // KHI TIẾN ĐỘ BÀI HÁT THAY ĐỔI
        audio.ontimeupdate = function () {
            if (audio.duration) {
                const progressPercent = Math.floor((audio.currentTime / audio.duration) * 100);
                progress.value = progressPercent;
                // on mobile
            }
            _this.displayRemainTime();
        }

        // KHI TUA SONG
        progress.onchange = function (e) {
            const seekTime = (audio.duration / 100) * e.target.value;
            audio.currentTime = seekTime;
        }


        // XOÁ CÁC THUỘC TÍNH KHI ACTIVE CŨ
        deleteActive = function() {
            songItems.forEach((songItem, index) => {
                songItem.classList.remove('songs-item-playing--active-onplay');
                songItem.classList.remove('songs-item--active');
                songItem.classList.remove('songs-item-playbtn--active');
            });
        }



        // KHI NEXT SONG
        nextBtn.onclick = function() {
            if (_this.isRandom) {
                _this.RandomSong();
                // không render list next song

            } else {
                _this.nextSong();
            }
            audio.play();

            _this.scrollToActiveSong();
            deleteActive();
        }

        //

        // KHI PREV SONG
        prevBtn.onclick = function() {
            if (_this.isRandom) {
                _this.RandomSong();
                // không render list next song



            } else {
                _this.prevSong();

            }
            audio.play();
            deleteActive();
            _this.scrollToActiveSong();
        }

        // KHI BẤM VÀO NÚT PLAY Ở THUMB BÀI BÁT Ở PHẦN TỔNG QUAN
        playBtnIcons.forEach((playBtnIcon, index) => {
            playBtnIcon.onclick = function() {
                if (_this.isPlaying && _this.currentIndex == index) {
                    audio.pause();
                } else if (!_this.isPlaying && _this.currentIndex == index) {
                    audio.play();
                } else if (_this.currentIndex != index) {
                    _this.currentIndex = index;
                    _this.loadCurrentSong();
                    audio.play();
                    deleteActive();

                }
                if(_this.isRandom) {


                } else if (!_this.isRandom && _this.currentIndex >= _this.songsData.length - 1) {

                } else {

                }
            }
        });


        // KHI BẬT NÚT CHẠY RANDOM
        randomBtn.onclick = function() {
            _this.isRandom = !_this.isRandom;
            _this.isRepeat = false;
            randomBtn.classList.toggle("music-control__icon-random--active", _this.isRandom);
            if (_this.isRandom) {
                randomBtn.style.color = 'var(--primary-color)';
            } else {
                randomBtn.style.color = '#fff';
            }
            repeatBtn.classList.toggle("music-control__icon-repeat--active", _this.isRepeat);

            if(_this.isRandom) {


            } else {
                if (_this.currentIndex >= _this.songsData.length - 1) {


                } else {

                }
            }
        }


        // KHI BẬT NÚT CHẠY REPEAT
        repeatBtn.onclick = function() {
            _this.isRepeat = !_this.isRepeat;
            _this.isRandom = false;
            // _this.setConfig("isRepeat", _this.isRepeat);
            repeatBtn.classList.toggle("music-control__icon-repeat--active", _this.isRepeat);
            if (_this.isRepeat === true) {
                repeatBtn.style.color = 'var(--primary-color)';
            }
            else {
                repeatBtn.style.color = 'var(--white-color)';
            }
            randomBtn.classList.toggle("music-control__icon-random--active", _this.isRandom);

        }



        // XỬ LÝ KHI AUDIO KẾT THÚC
        audio.onended = function () {
            if (_this.isRepeat) {
              audio.play();
            } else {
              nextBtn.click();

            }
        };

        // KHI CLICK DUP VÀO BÀI NHẠC THÌ PHÁT NHẠC
        songItems.forEach((songItem, index) => {
            songItem.ondblclick = function() {
                _this.currentIndex = index;
                _this.loadCurrentSong();
                deleteActive();
                audio.play();

                if(_this.isRandom) {
                    // không render next song list
                } else {

                }
            }

        })

        // xử lý khung login
        function closeLogin() {
            $('.form-login').classList.remove('show');
            $('#overlay-login').classList.remove('show');
        };

        $('.header__login-btn').onclick = function() {

            $('.form-login').classList.add('show');
            $('#overlay-login').classList.add('show');
        };

        $('.close-login').onclick = closeLogin;

        $('#overlay-login').onclick = closeLogin;

        // Xử lý khung add song playlist
        if ($('.songs-item-right-more') !== null) {
            function closeAddPlaylist() {
                $('.add-playlist').classList.remove('show');
                $('#overlay-add-playlist').classList.remove('show');
            };

            $$('.songs-item-right-more').forEach((item) => {
               item.onclick = function() {

                   if ($('.add-playlist') === null) {
                       let alert = $('.div-alert');
                       const htmlString = `
                                        <div class="alert alert-error">
                                            Vui lòng đăng nhập để sử dụng chức năng này
                                        </div>
                                    `;
                       alert.innerHTML = htmlString;
                   }

                    $('.add-playlist').classList.add('show');
                    $('#overlay-add-playlist').classList.add('show');
                   $$('.form__add-song').forEach((it) => {
                       it.onclick = function () {
                            let songId = item.getElementsByTagName("input")[0].value;
                            let playlistId = it.getElementsByTagName("input")[0].value;
                            let url = "http://localhost:8080/WalletMusic_war_exploded/api-playlistInc";
                            url = url + "?playlistId=" + playlistId + "&" + "songId=" + songId;
                            var alert = $('.div-alert');
                            var alertType = "success";
                            var message = "Thêm thành công";
                           fetch(url, {
                               method: 'POST',
                           })
                               .then(response => response.json()) // chuyển đổi response sang dạng JSON
                               .then(data => {
                                   closeAddPlaylist();

                                   const htmlString = `
                                        <div class="alert alert-${alertType}">
                                            ${message}
                                        </div>
                                    `;
                                   alert.innerHTML = htmlString;

                               })
                               .catch(error => {
                                   closeAddPlaylist();
                                   alertType = "error";
                                   message = "Bài hát đã có trong playlist";

                                   const htmlString = `
                                        <div class="alert alert-${alertType}">
                                            ${message}
                                        </div>
                                    `;
                                   alert.innerHTML = htmlString;
                               });
                       }
                   });
                }


            });

            if ($('.add-close') !== null) {
                $('.add-close').onclick = closeAddPlaylist;
            }
            if ($('#overlay-add-playlist') !== null) $('#overlay-add-playlist').onclick = closeAddPlaylist;



        }



        // Xử lý khung register
        function closeRegister() {
            $('.form-register').classList.remove('show');
            $('#overlay-register').classList.remove('show');
        };

        $('.header__register-btn').onclick = function() {

            $('.form-register').classList.add('show');
            $('#overlay-register').classList.add('show');
        };

        $('.close-register').onclick = closeRegister;

        $('#overlay-register').onclick = closeRegister;


        // xử lý khung tạo playlist mới
        function showCreate() {
            $('.create-playlist').classList.add('show');
            $('#overlay').classList.add('show');
        };

        function closeCreate() {
            $('.create-playlist').classList.remove('show');
            $('#overlay').classList.remove('show');
        };

        $('.sidebar__add-playlist').onclick = showCreate;


        $('.create-close').onclick = closeCreate;
        $('#overlay').onclick = closeCreate;


        // CUỘN LÊN THÌ LÀM TRONG THANH HEADER
        mainContainer.onscroll = function() {
            scrollTop = mainContainer.scrollY || mainContainer.scrollTop
            // if (scrollTop > 50) {
            //     header.classList.toggle('header--active');
            // }

            // làm cách này mà ko làm cách trên để tránh bị gật lag
            if(scrollTop > 5) {
                Object.assign(header.style, {
                    backgroundColor: `#2c2a28`,
                    boxShadow: '0 3px 5px rgba(0,0,0,0.1)',
                })
            } else {
                Object.assign(header.style, {
                    backgroundColor: 'transparent',
                    boxShadow: 'none',
                })
            }
        };


        search.onkeypress = function(event) {
            let text = this.value;
            if (event.key === "Enter" && text !== '') {

                window.location.href = 'http://localhost:8080/WalletMusic_war_exploded/tim-kiem?keyword=' + text;
            }
        };

        search.onkeyup = function() {
            $('.js__suggest-keywords').style.display = 'none';
            $('.js_overlay-search').classList.add('show');
            $('.js__related-keywords').style.display = 'block';
            let url = "http://localhost:8080/WalletMusic_war_exploded/api-search?keyword=" + search.value;
            if (search.value.length > 0) {
                fetch(url)
                    .then(respond => respond.json())
                    .then(data => {
                        console.log(data);
                        const listResult = data['result'];
                        listResult.forEach((item) => {
                            var listSearch = $('.js-search');
                            const htmls = listResult.map((song,index) => {
                                return index < 5 ? `<li class="header__width-search-sub-item">
                                    <a  href="http://localhost:8080/WalletMusic_war_exploded/tim-kiem?keyword=${song}" class="header__width-search-sub-item-link">
                                        <i class="fas fa-arrows-alt-h "></i>
                                        <span class="">${song}</span>
                                    </a>
                                </li>` : ''
                            })

                            listSearch.innerHTML = htmls.join('');

                        })
                    })
            }


        }

        search.onclick = () => {
            $('.header__width-search-sub').style.display = 'block';
            $('.overlay-search').classList.add('show');
        }

        $$('.overlay-search').forEach((item,index) => {
            item.onclick = () => {
                $$('.header__width-search-sub').forEach((item2) => {
                    item2.style.display = 'none';
                })
                $('.overlay-search').classList.remove('show');
            }
        })

        // $('.overlay-search').onclick = () => {
        //     $('.header__width-search-sub').style.display = 'none';
        //     $('.overlay-search').classList.remove('show');
        // }



        // KHI CLICK SETTING
        headerSetting.onclick = function(e) {
            headerSetting.classList.toggle('header__setting--active');
        }
        headerSettingList.onclick = function(e) {
            e.stopPropagation();
            headerSetting.classList.remove('header__setting--active');
        }

        $('.header__user').onclick = function(e) {
            $('.header__user').classList.toggle('header__user-setting--active');
        };

        $('.header__user-setting-list').onclick = function(e) {
            e.stopPropagation();
            $('.header__user').classList.remove('header__user-setting--active');
        };





        // TOAST
        $$('.js__toast').forEach((item, index) => {
            item.onclick = function() {
                _this.toastSlide();
            }
        })






    },


    //=================================================================
    start: function() {
        // Define các thuộc tính cho object
        this.defineProperties();

        // xử lý và sự kiện
        this.handleEvents();

        // hiển thị thời gian chạy và thời lượng của audio hiện tại
        this.displayDurationTime();

        // theme
        // this.applyTheme();

        this.loadCurrentSong();

        // this.musicNote();
    }

}


function loadListSong() {
    songItems.forEach(function(item) {
        // let bg = item.querySelector('.songs-item-left-img').style.backgroundImage;
        // bg = bg.substr(5,bg.length-7);
        // console.log(bg);
        // item.querySelector('.songs-item-left-img').style.backgroundImage,
        const song = {
            background: item.querySelector('.songs-item-left-img').style.backgroundImage,
            name: item.querySelector('.songs-item-left-body-name').textContent,
            singer: item.querySelector('.songs-item-left-body-singer').textContent,
            pathSong: item.querySelector('.audio').src,
            duration : item.querySelector('.songs-item-right-duration').textContent,
        }
        app.songsData.push(song);
    });
}

loadListSong();
app.start();










