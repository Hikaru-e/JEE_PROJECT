/*!
 * codebase - v5.7.0
 * @author pixelcave - https://pixelcave.com
 * Copyright (c) 2023
 */
!(function () {
    class e {
        static initValidation() {
            Codebase.helpers("jq-validation"),
                jQuery(".js-validation").validate({
                    ignore: [],
                    rules: {
						"sujet_stage": { required: !0, minlength: 3 },
                        "val-username": { required: !0, minlength: 3 },
                        "login-email": { required: !0, emailWithDot: !0 },
                        "login-password": { required: !0, minlength: 5 },
                        "val-confirm-password": {
                            required: !0,
                            equalTo: "#val-password",
                        },
                        "val-suggestions": { required: !0, minlength: 5 },
                        "val-skill": { required: !0 },
                        "val-currency": { required: !0, currency: ["$", !0] },
                        "val-website": { required: !0, url: !0 },
                        "val-phoneus": { required: !0, phoneUS: !0 },
                        "val-digits": { required: !0, digits: !0 },
                        "val-number": { required: !0, number: !0 },
                        "val-range": { required: !0, range: [1, 5] },
                        "val-terms": { required: !0 },
                        "val-select2": { required: !0 },
                        "val-select2-multiple": { required: !0, minlength: 2 },
                    },
                    messages: {
                        "val-username": {
                            required: "Please enter a username",
                            minlength:
                                "Your username must consist of at least 3 characters",
                        },
                        "val-email": "Please enter a valid email address",
                        "val-password": {
                            required: "Please provide a password",
                            minlength:
                                "Your password must be at least 5 characters long",
                        },
                        "val-confirm-password": {
                            required: "Please provide a password",
                            minlength:
                                "Your password must be at least 5 characters long",
                            equalTo: "Please enter the same password as above",
                        },
                        "val-select2": "Please select a value!",
                        "val-select2-multiple":
                            "Please select at least 2 values!",
                        "val-suggestions": "What can we do to become better?",
                        "val-skill": "Please select a skill!",
                        "val-currency": "Please enter a price!",
                        "val-website": "Please enter your website!",
                        "val-phoneus": "Please enter a US phone!",
                        "val-digits": "Please enter only digits!",
                        "val-number": "Please enter a number!",
                        "val-range": "Please enter a number between 1 and 5!",
                        "val-terms": "You must agree to the service terms!",
                    },
                }),
                jQuery(".js-select2").on("change", (e) => {
                    jQuery(e.currentTarget).valid();
                });
        }
        static init() {
            this.initValidation();
        }
    }
    Codebase.onLoad(() => e.init());
})();
