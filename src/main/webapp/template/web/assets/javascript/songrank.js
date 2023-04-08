// const $ = document.querySelector
document.querySelector('.js__zingchart__100more').onclick = function() {
    document.querySelector('.zingchart__more-rank').classList.add('show');
    document.querySelector('.zingchart__100more').style.opacity = 0;
    document.querySelector('.js__zingchart__100more').style.cursor = 'default';
}
