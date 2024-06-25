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