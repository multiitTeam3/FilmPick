$(document).ready(
        function(){

            function findImgList(){

                $.ajax({

                    url: 'movie/findMovieList',
                    method: 'POST',
                    contentType: 'application/json',
                    "success": function(list){
                        console.log(list);

                        $.each(list, function(index, movie){

                            console.log(movie.posterPath)

                            $("#posterImg").append(
                                '<img src="' + movie.posterPath + '" alt="no img"><br><br>')

                            $("#posterImg").append(
                            '<button onclick="location.href=\'/movie/reservationtime\'">예매하기</button><br>')
                        })
                    }
                })
            }
            findImgList()
        })