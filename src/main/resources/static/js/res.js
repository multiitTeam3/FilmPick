$(document).on('click','.plus1',function(){
            $('.a1').toggleClass('none');
        })

        $(document).on('click','.plus2',function(){
            $('.a2').toggleClass('none');
        })

        <!--예약 전체 내역 페이지 켜지며 불러오기-->

         $(document).ready(
            function(){



                function findReservationList(page){

                    $('#reservations').html("");

                    $.ajax({

                        url: '/movie/findAllReservationByMemberNo?page=' + page,
                        method: 'POST',
                        contentType: 'application/json',
                        success: function(responseDTO){

                            var vwResDataGroupDTO = responseDTO.reservations;
                            var counts = responseDTO.count;
                            var pages = responseDTO.pages;




                            $.each(vwResDataGroupDTO, function(index, reservation){

                                console.log(reservation.memberNo);
                                var scheduleNo = reservation.scheduleNo;
                                var movieNo = reservation.movieNo;
                                console.log("scheduleNo: "+scheduleNo)

                                <!--미결제 처리-->
                                if(reservation.rsvIsPaid=="N"){
                                    $('#reservations').append('<div class="reservation"><p>'  +   reservation.date   +
                                    '</p><p><span class="title">' +  reservation.movieTitle  +
                                    '</span><button id="update">예약 변경</button><button id="delete">취소하기</button></p><div class="mov-content"><div class="img"><img src="' +
                                    reservation.posterPath  +
                                    '" width="60%"></div><div class="content1"><p>' +
                                    reservation.cinemaName + '</p><p>' + reservation.screenName  +
                                    '</p><p>' + reservation.startTime + '~' + reservation.endTime +
                                    '</p><p> 성인 ' + reservation.adultNum + '인, 청소년 ' +
                                    reservation.teenNum + '인(' + reservation.seatNameList +
                                ') </p><button class="pay" data-schedule-no=' +  scheduleNo  + '>'
                                + reservation.totalPrice   +'원 결제하기</button></div></div></div>')

                                }

                                <!--결제한 것-->
                                if(reservation.rsvIsPaid=="Y"){

                                    $('#reservations').append('<div class="reservation"><p>' +  reservation.date +
                                    '</p><p><span class="title">' + reservation.movieTitle +
                                    '</span><span>결제완료</span></p><div class="mov-content"><div class="img"><img src="' +
                                    reservation.posterPath +
                                    '" width="60%"></div><div class="content1"><p>' +
                                    reservation.cinemaName + '<p><p>' + reservation.screenName + '</p><p>' + reservation.startTime + '~' + reservation.endTime + '</p><p> 성인 ' + reservation.adultNum + '인, 청소년 ' +
                                    reservation.teenNum + '인(' + reservation.seatNameList +
                                ') </p><a href="/movie/review?movieNo='
                                + movieNo
                                + '"><button class="review" data-movie-no=' +  movieNo + '>감상평 남기기</button></a></div></div></div>')
                                }

                            })

                             $('.pageNums').html("");

                             for(var p=1; p<=pages; p++){
                                $('.pageNums').append('<button class="pagesAll">'+ p  + '</button>');
                             }

                        }
                    })

                }



                function findNotPaidReservationList(page){

                    $('#reservations').html("");

                    $.ajax({

                        url: '/movie/findAllNotPaidReservationByMemberNo?page=' + page,
                        method: 'POST',
                        contentType: 'application/json',
                        success: function(responseDTO){

                            var vwResDataGroupDTO = responseDTO.reservations;
                            var counts = responseDTO.count;
                            var pages = responseDTO.pages;

                            $.each(vwResDataGroupDTO, function(index, reservation){


                                console.log(reservation.memberNo);

                                var scheduleNo = reservation.scheduleNo;

                                console.log("scheduleNo: "+scheduleNo)


                                $('#reservations').append('<div class="reservation"><p>'  +   reservation.date   +
                                '</p><p><span class="title">' +  reservation.movieTitle  +
                                '</span><button id="update">예약 변경</button><button id="delete">취소하기</button></p><div class="mov-content"><div class="img"><img src="' +
                                reservation.posterPath  +
                                '" width="60%"></div><div class="content1"><p>' +
                                reservation.cinemaName + '</p><p>' + reservation.screenName  +
                                '</p><p>' + reservation.startTime + '~' + reservation.endTime +
                                '</p><p> 성인 ' + reservation.adultNum + '인, 청소년 ' +
                                reservation.teenNum + '인(' + reservation.seatNameList +
                                ') </p><button class="pay" data-schedule-no=' +  scheduleNo  + '>'
                                + reservation.totalPrice   +'원 결제하기</button></div></div></div>')





                            })

                             $('.pageNums').html("");

                             for(var p=1; p<=pages; p++){
                                $('.pageNums').append('<button class="pagesNotPaid">'+ p  + '</button>');
                             }

                        }
                    })

                }

                findReservationList(1);

                $(document).on('click','.pagesAll',function(){
                     const page = $(this).text();
                     findReservationList(page);

                })


                $(document).on('click','#all',function(){
                    findReservationList(1);
                })

                $(document).on('click','#notpaid',function(){
                    findNotPaidReservationList(1);
                })


                $(document).on('click','.pagesNotPaid',function(){
                     const page = $(this).text();
                     findNotPaidReservationList(page);

                })


                // 결제하기 버튼 클릭 이벤트
                function payTheBill(scheduleNo){

                    $.ajax({

                        url: '/movie/payTheBill',
                        method: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({ scheduleNo : scheduleNo }),
                        success: function(list){

                            console.log("success/movie/payTheBill");

                            var encodedList = encodeURIComponent(JSON.stringify(list));

                            window.location.href='/movie/reservationseat/payment?list=' + encodedList;
                        }

                    })

                }


                $(document).on('click','.pay',function(){
                     const scheduleNo = $(this).data('schedule-no');

                     console.log("scheduleNo : ??? : "+scheduleNo);

                     payTheBill(scheduleNo);

                })





            }
        )