        $(document).ready(
           function(){

               // 테이블 자료 이용함
               function findEnrolledList(page){

                   $('#list').html("");

                   $.ajax({

                       url: '/admin/movie/findAllEnrolledMovie?page=' + page,
                       method: 'POST',
                       contentType: 'application/json',
                       success: function(responseDTO){

                           var list = responseDTO.list;
                           var pages = responseDTO.pages;
                           var count = responseDTO.count;

                           $('#list').html("");

                           $.each(list, function(index, movie){

                                var adultContent = movie.adult ==0? "해당없음" : "성인영화";

                                var lessContent = movie.movieContent.length > 20?
                                movie.movieContent.substring(0,30) + "....."
                                : movie.movieContent;

                                var index1 = (index +1) + (page-1)*3;

                                var htmlContent = `

                                    <tr>
                                        <th scope="row">${index1}</th>
                                        <td id="movieNo">${movie.movieNo}</td>
                                        <td id="movieTitle">${movie.movieTitle}</td>
                                        <td id="genreContent">${movie.genreContent}</td>
                                        <td id="popularity">${movie.popularity}</td>
                                        <td id="duration">${movie.duration}</td>
                                        <td id="movieContent">${lessContent}</td>
                                        <td id="adult">${adultContent}</td>
                                        <td id="image"><img src="${movie.posterPath}"
                                                            width="40px"></td>
                                        <td id="avgRate">${movie.avgRate}</td>
                                        <td id="ticketSold">${movie.ticketSold}</td>


                                    </tr>

                                `;

                                $('#list').append(htmlContent);

                           })

                           $('.pageNums1').html("");

                             for(var p=1; p<=pages; p++){
                                $('.pageNums1').append('<button class="pages1">'+ p  + '</button>');
                             }

                       }

                   })

               }


                findEnrolledList(1);

                $(document).on('click','.pages1',function(){
                     const page = $(this).text();
                     findEnrolledList(page);
                })


                // api 이용
               function findAPIMovieList(page){

                   $('#list2').html("");

                   $.ajax({

                       url: '/admin/movie/findAPIMovieList?page=' + page,
                       method: 'POST',
                       contentType: 'application/json',
                       success: function(list){


                           $.each(list, function(index, movie){

                                var adultContent = movie.adult ==0? "해당없음" : "성인영화";

                                var lessContent = movie.movieContent.length > 20?
                                movie.movieContent.substring(0,30) + "....."
                                : movie.movieContent;
                                console.log("movieId : " + movie.id)

                                var index1 = (index +1) + (page-1)*20;

                                var htmlContent = `

                                    <tr>

                                        <th scope="row">${index1}</th>
                                        <td id="movieTitle">${movie.movieTitle}</td>
                                        <td id="genreNo">${movie.genreNo}</td>
                                        <td id="popularity">${movie.popularity}</td>
                                        <td id="duration">${movie.duration}</td>
                                        <td id="movieContent">${lessContent}</td>
                                        <td id="adult">${adultContent}</td>
                                        <td id="image"><img src="${movie.posterPath}"
                                                            width="40px"></td>
                                        <td id="insertBtn">
                                         <button id="insertMovie" val="${movie.id}">추가</button>
                                         </td>

                                    </tr>

                                `;

                                $('#list2').append(htmlContent);


                           })

                       }

                   })

               }


                findAPIMovieList(1);


                 for(var p=1; p<=10; p++){
                    $('.pageNums2').append('<button class="pages2">'+ p  + '</button>');
                 }


                $(document).on('click','.pages2',function(){
                     const page = $(this).text();
                     findAPIMovieList(page);

                })


                $(document).on('click','#insertMovie',function(){
                     var movieId = $(this).attr('val');
                     console.log("click movieId : "+movieId)
                     var button = $(this);

                      $.ajax({

                           url: '/admin/movie/insertAPIMovie?id=' + movieId,
                           method: 'POST',
                           contentType: 'application/json',
                           success: function(response){
                                alert(response);
                                button.text("영화추가 완료");
                                button.addClass('unclickable');
                                button.prop('disabled',true);
                                findAPIMovieList(1);
                           }

                      })

                })


                // 장르 삽입
                $(document).on('click','#insertGenre',function(){

                    var button = $(this);

                     $.ajax({

                       url: '/admin/movie/insertGenre',
                       method: 'POST',
                       contentType: 'application/json',
                       success: function(response){
                            console.log(response);
                            button.text("장르추가 완료");
                            button.addClass('unclickable');
                            button.prop('disabled',true);
                       }

                     })



                })

           }
        )




