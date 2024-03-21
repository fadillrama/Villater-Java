(function(){
            addContactButtonListener();
            addCloseButtonListener();
        }())

        function addContactButtonListener(){
            $('.contact-button').click(function(event){
                let employeeNumber = $(this).attr('data-id');
                let urlEndpoint = `/api/salesman/contact/${employeeNumber}`;

                $.ajax({
                    url : urlEndpoint,
                    success: function({address, city, phone}){

                        $('.contact-dialog .address').text(address);
                        $('.contact-dialog .city').text(city);
                        $('.contact-dialog .phone').text(phone);

                        $('.modal-layer').addClass('modal-layer--opened');
                        $('.contact-dialog').addClass('popup-dialog--opened');
                    }
                });
            });
        }

        function addCloseButtonListener(){
            $('.close-button').click(function(event){
                $('.modal-layer').removeClass('modal-layer--opened');
                $('.popup-dialog').removeClass('popup-dialog--opened');
            });
        }