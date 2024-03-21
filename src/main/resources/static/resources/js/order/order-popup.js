(function(){
    addDestinationButtonListener();
    addCloseButtonListener();
}())

function addDestinationButtonListener(){
    $('.destination-button').click(function(event){
        let invoiceNumber = $(this).attr('data-invoiceNumber');

        $.ajax({
            url: `/api/order/destination/${invoiceNumber}`,
            success: function({destinationAddress, destinationCity, destinationPostalCode}){
                $('.destination-dialog .destination-address').text(destinationAddress);
                $('.destination-dialog .destination-city').text(destinationCity);
                $('.destination-dialog .destination-postal-code').text(destinationPostalCode);

                $('.modal-layer').addClass('modal-layer--opened');
                $('.destination-dialog').addClass('popup-dialog--opened');
            }
        });
    });
}

function addCloseButtonListener(){
    $('.close-button').click(function(event){
        $('.modal-layer').removeClass('modal-layer--opened');
        $('.popup-dialog').removeClass('popup-dialog--opened');
        $('.popup-dialog input').val("");
        $('.popup-dialog textarea').val("");
        $('.popup-dialog .validation-message').text("");
    });
}
