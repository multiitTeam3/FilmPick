$(function() {
    console.log("제이쿼리 로드 확인");



    // 비밀번호 확인
    $('#password, #confirmPassword').on('blur', function() {
        let password = $('#password').val();
        let confirmPassword = $('#confirmPassword').val();
        if (password !== confirmPassword) {
            $('#passwordError').show();
        } else {
            $('#passwordError').hide();
        }
    });
});

// KaKao Map 주소 API
function findAddr() {
    new daum.Postcode({
        oncomplete: function(data) {
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수

            // jQuery를 사용하여 요소 값 설정
            if (roadAddr !== '') {
                $('#address').val(roadAddr);
            } else if (jibunAddr !== '') {
                $('#address').val(jibunAddr);
            }
        }
    }).open();
}