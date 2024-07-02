$(document).ready(
        function(){

            function findImgList(){

                $.ajax({

                    url: 'movie/findMovieList',
                    method: 'POST',
                    contentType: 'application/json',
                    success: function(list){
                        console.log(list);

                        $.each(list, function(index, movie){

                            console.log(movie.posterPath)

                            $("#posterImg").append(
                                '<div class="oneMovie"><img width=600px src="' + movie.posterPath +
                                '" alt="no img"><button class="mainBtn" onclick="location.href=\'/movie/reservationtime\'">예매하기</button></div>')
                        })
                    }
                })
            }
            findImgList()
        })