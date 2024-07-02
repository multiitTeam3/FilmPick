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
                            var poster = movie.posterPath;
                            var title = movie.movieTitle;
                            var content = movie.movieContent.substring(0,10)+"..."


                            $(".wrap").append(
                                `
                                    <div class="card" style="width:300px">
                                      <img class="card-img-top" src="${poster}" alt="Card image cap">
                                      <div class="card-body">
                                        <h5 class="card-title">${title}</h5>
                                        <p class="card-text">${content}</p>
                                        <a href="/movie/reservationtime" class="btn btn-primary">예매하기</a>
                                      </div>
                                    </div>
                                `
                            )
                        })
                    }
                })
            }
            findImgList()
        })