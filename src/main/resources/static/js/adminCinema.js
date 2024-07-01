$(document).ready(function() {

    $('#cinemaUpdateModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);

        let cinemaNo = button.data('no');
        let cinemaName = button.data('name');
        let cinemaRegion = button.data('regionno');
        let cinemaAddr = button.data('cinemaaddr');
        let openTime = button.data('opentime');
        let closeTime = button.data('closetime');

        console.log("cinemaNo:", cinemaNo);
        console.log("cinemaName:", cinemaName);
        console.log("cinemaRegion:", cinemaRegion);
        console.log("cinemaAddr:", cinemaAddr);
        console.log("openTime:", openTime);
        console.log("closeTime:", closeTime);

        let modal = $(this);
        modal.find('#updateCinemaNo').val(cinemaNo);
        modal.find('#updateCinemaName').val(cinemaName);
        modal.find('#updateRegion').val(cinemaRegion);
        modal.find('#updateAddress').val(cinemaAddr);
        modal.find('#updateOpenTime').val(openTime);
        modal.find('#updateCloseTime').val(closeTime);
    });
});