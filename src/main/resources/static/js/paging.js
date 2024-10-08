$(function() {
    console.log("페이징 확인");
    let page = parseInt($('.pages').eq(0).text());

    // 페이지 버튼 초기 활성화 설정
    $('.pages').eq(0).addClass('active');

    // 페이지 버튼 클릭 이벤트 처리
    $('.pages').click(function() {
        $('.pages').removeClass('active');
        $(this).addClass('active');

        page = parseInt($(this).text());
        pageing(page);
    });

    // 이전 버튼 클릭 이벤트 처리
    $('.previous').click(function() {
        if (page > 1) {
            $('.pages').removeClass('active');
            page -= 1;
            $('.pages').eq(page - 1).addClass('active');

            pageing(page);
        }
    });

    // 다음 버튼 클릭 이벤트 처리
    $('.next').click(function() {
        if (page < pages) {
            $('.pages').removeClass('active');
            page += 1;
            $('.pages').eq(page - 1).addClass('active');

            pageing(page);
        }
    });

    // 페이징 처리 함수
    function pageing(page) {
        $.ajax({
            url: "/admin/membermanagement/paging",
            data: {
                page: page
            },
            success: function(result) {
                console.log(result);
                $('#d1').html(result);
            },
            error: function() {
                alert('실패.@@@');
            }
        });
    }
});