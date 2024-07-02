$(document).ready(function() {

    function findImgList() {

        $.ajax({
            url: 'movie/findMovieList',
            method: 'POST',
            contentType: 'application/json',
            success: function(list) {
                console.log(list);

                // 최대 4개의 영화를 처리
                var maxMovies = 4;
                var limitedList = list.slice(0, maxMovies);

                // 요소를 초기화
                var carouselInner = $(".carousel-inner");
                carouselInner.empty();

                $.each(limitedList, function(index, movie) {

                    console.log(movie.posterPath)

                    var activeClass = index === 0 ? 'active' : '';
                    carouselInner.append(
                        '<div class="carousel-item ' + activeClass + '">' +
                        '<img src="' + movie.posterPath + '" class="d-block w-100" alt="...">' +
                        '<div class="carousel-caption d-none d-md-block">' +
                        '<h5>' + movie.title + '</h5>' +
                        '<p>' + movie.description + '</p>' +
                        '</div></div>');
                })
            }
        })
    }

    findImgList();
})