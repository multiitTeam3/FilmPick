$(function() {
    $('#nav-tab .nav-link').click(function(e) {
        e.preventDefault();
        let tab = $(this).data('bs-target');
        let tabContent = tab.replace('#', '');

        $.ajax({
            url: "/Customer/" + tabContent,
            type: 'GET',
            success: function(data) {
                $('#' + tabContent).html(data);

                $('.nav-link').removeClass('active');
                $('.tab-pane').removeClass('show active');

                $(this).addClass('active');
                $('#' + tabContent).addClass('show active');
            },
            error: function() {
                alert('서버 오류 실패');
            }
        });
    });

    $.ajax({
        url: "/Customer/noticeInfo",
        type: 'GET',
        success: function(data) {
            $("#noticeInfo").html(data);
        },
        error: function() {
            alert('서버 오류 실패');
        }
    });


});