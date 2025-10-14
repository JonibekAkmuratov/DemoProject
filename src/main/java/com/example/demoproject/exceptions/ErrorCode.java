package com.example.demoproject.exceptions;


public final class ErrorCode {

    public static final String UNKNOWN_ERROR = "unknown_error";
    public static final String ACCESS_DENIED = "access_denied";
    public static final String INPUT_CANNOT_BE_NULL = "input_cannot_be_null";
    public static final String INPUT_CANNOT_BE_BLANK = "input_cannot_be_blank";
    public static final String JURIDICAL_TIN_CANNOT_BE_BLANK = "juridical_tin_cannot_be_blank";
    public static final String FOREIGN_TIN_IS_RESTRICTED_FROM_GENERATING_KEY = "foreign_tin_is_restricted_from_generating_key";
    public static final String INPUT_MUST_BE_PAST_OR_PRESENT_TIME = "input_must_be_past_or_present_time";
    public static final String INPUT_MUST_BE_FUTURE_OR_PRESENT_TIME = "input_must_be_future_or_present_time";
    public static final String INPUT_MUST_BE_VALID_PHONE_NUMBER = "input_must_be_valid_phone_number";
    public static final String NOT_SUPPORTED_OPERATION = "not_supported_operation";
    public static final String INVALID_PATH = "invalid_path";
    public static final String INVALID_DATE = "invalid_date";

    public static final String NOT_FOUND = "not_found";
    public static final String KEY_IS_NOT_FOUND_BY_ID = "key_is_not_found_by_id";
    public static final String BAD_CREDENTIALS = "bad_credentials";
    public static final String FILE_SIZE_EXCEEDED_LIMIT = "file_size_exceeded_limit";
    public static final String FILE_IS_NOT_READABLE = "file_is_not_readable";
    public static final String FILE_NOT_FOUND = "file_not_found";
    public static final String FILE_FINGERPRINT_NOT_FOUND = "file_fingerprint_not_found";
    public static final String SOMETHING_WENT_WRONG_WITH_ATTACHING_PKCS7_WITH_EIMZO_SERVER = "something_went_wrong_on_attaching_pkcs7_with_eimzo_server";
    public static final String SOMETHING_WENT_WRONG_WITH_CREATING_KEY = "something_went_wrong_with_creating_key";
    public static final String NS10_CODE_EXISTS = "ns10_code_exists";
    public static final String NS10_CODE_NOT_FOUND = "ns10_code_not_found";
    public static final String NS11_CODE_EXISTS = "ns11_code_exists";
    public static final String NS11_CODE_NOT_FOUND = "ns11_code_not_found";
    public static final String SUBJECT_NOT_FOUND = "subject_not_found";
    public static final String OVIR_REGION_CODE_ALREADY_EXISTS = "ovir_region_code_already_exists";

    public static final String INTERNAL_SERVER_ERROR = "internal_server_error";
    public static final String CERTIFICATE_NOT_FOUND_IN_CAMS = "certificate_not_found_in_cams";
    public static final String REVOKED_KEY_CANNOT_BE_UPDATED = "revoked_key_cannot_be_updated";

    public static final String KEY_NOT_FOUND = "key_not_found";
    public static final String APPLICATION_NOT_FOUND = "application_not_found";
    public static final String PKCS7_REQUIRED = "pkcs7_required";
    public static final String PKCS7_FILE_RESOURCE_URL_REQUIRED = "pkcs7_file_resource_url_required";
    public static final String PKCS10_FILE_RESOURCE_URL_REQUIRED = "pkcs10_file_resource_url_required";
    public static final String SELFIE_FILE_RESOURCE_URL_REQUIRED = "selfie_file_resource_url_required";
    public static final String PASSPORT_FILE_RESOURCE_URL_REQUIRED = "passport_file_resource_url_required";
    public static final String PINFL_REQUIRED = "pinfl_required";
    public static final String NS10_CODE_REQUIRED = "ns10_code_required";
    public static final String NS11_CODE_REQUIRED = "ns11_code_required";
    public static final String PHONE_REQUIRED = "phone_required";
    public static final String X500_NAME_REQUIRED = "x500_name_required";
    public static final String TIMESTAMP_TOKEN_REQUIRED = "timestamp_token_required";
    public static final String TWO_STEP_VERIFICATION_IS_NOT_ENABLED = "two_step_verification_is_not_enabled";
    public static final String OTP_NOT_MATCHED = "otp_not_matched";
    public static final String LOGIN_REQUIRED = "login_required";
    public static final String ADDRESS_REQUIRED = "address_required";
    public static final String POSITION_REQUIRED = "position_required";
    public static final String LGOTA_FILE_REQUIRED = "lgota_file_required";
    public static final String TIN_NOT_FOUND = "tin_not_found";
    public static final String TIN_IS_NOT_ACTIVE = "tin_is_not_active";
    public static final String TIN_IS_INVISIBLE = "tin_is_invisible";
    public static final String PINFL_IS_INVISIBLE = "pinfl_is_invisible";
    public static final String MOBILE_IS_INVISIBLE = "mobile_is_invisible";
    public static final String EMAIL_IS_INVISIBLE = "email_is_invisible";
    public static final String TIN_IS_NOT_ALLOWED_TO_CREATE_KEY = "tin_is_not_allowed_to_create_key";
    public static final String SMS_ALREADY_SENT = "sms_already_sent";
    public static final String SUBJECT_NOT_FOUND_BY_TIN = "subject_not_found_by_tin";

    public static final String KEY_IS_EXPIRED = "key_is_expired";
    public static final String KEY_STATE_IS_ALREADY_REVOKED = "key_state_is_already_revoked";
    public static final String KEY_TYPE_REQUIRED = "key_type_required";
    public static final String KEY_NAME_REQUIRED = "key_name_required";
    public static final String KEY_MOBILE_IS_EMPTY_OR_NULL = "key_mobile_is_empty_or_null";
    public static final String PINFL_IS_NOT_FOUND_IN_PKCS7 = "pinfl_is_not_found_in_pkcs7";
    public static final String KEY_NOT_FOUND_BY_SERIAL_NUMBER = "key_not_found_by_serial_number";
    public static final String INVALID_KEY_STATE = "invalid_key_state";
    public static final String INVALID_NATIONALITY_SELECTED = "invalid_nationality_selected";
    public static final String INVALID_NATIONALITY_TIN = "invalid_nationality_tin";
    public static final String INVALID_REPORT_TYPE = "invalid_report_type";
    public static final String INVALID_ISSUER_ROLE = "invalid_issuer_role";
    public static final String USER_ROLE_IS_NOT_ALLOWED = "user_role_is_not_allowed";
    public static final String ISSUER_NOT_FOUND = "issuer_not_found";
    public static final String INVALID_JURIDIC_KEY_ROLE = "invalid_juridic_key_role";
    public static final String INVALID_JURIDICAL_TIN = "invalid_juridical_tin";
    public static final String INVALID_KEY_NAME = "invalid_key_name";
    public static final String KEY_CREATE_DATE_IS_OUT_OF_RECREATION_PERIOD = "key_create_date_is_out_of_recreation_period";
    public static final String INVALID_PINFL = "invalid_pinfl";
    public static final String INVALID_PINFL_OR_JURIDIC_TIN = "invalid_pinfl_or_juridic_tin";
    public static final String INVALID_PRIVILEGE_ID = "invalid_privilege_id";
    public static final String INVALID_PHONE_NUMBER = "invalid_phone_number";
    public static final String NO_PRIVILEGE_FOR_JURIDICAL_TIN = "no_privilege_for_juridical_tin";
    public static final String INVALID_TIN_OR_PINFL = "invalid_tin_or_pinfl";
    public static final String CITIZEN_NOT_FOUND = "citizen_not_found";
    public static final String CITIZEN_NOT_FOUND_IN_IRON_BOOK = "citizen_not_found_in_iron_book";
    public static final String CITIZEN_REMOVED_FROM_IRON_BOOK = "citizen_removed_from_iron_book";
    public static final String CITIZEN_NOT_FOUND_IN_YOUTH_BOOK = "citizen_not_found_in_youth_book";
    public static final String CITIZEN_REMOVED_FROM_YOUTH_BOOK = "citizen_removed_from_youth_book";
    public static final String CITIZEN_NOT_FOUND_IN_WOMEN_BOOK = "citizen_not_found_in_women_book";
    public static final String CITIZEN_REMOVED_FROM_WOMEN_BOOK = "citizen_removed_from_women_book";

    public static final String CITIZEN_NOT_FOUND_IN_DISABLED_BOOK = "citizen_not_found_in_disabled_book";
    public static final String CITIZEN_REMOVED_FROM_DISABLED_BOOK = "citizen_removed_from_disabled_book";
    public static final String PRIVILEGE_IS_ALREADY_USED = "privilege_is_already_used";
    public static final String KEY_ROLE_IS_NOT_ALLOWED = "key_role_is_not_allowed";
    public static final String INVALID_KEY_ROLE_AND_USER_TYPE_FIELDS = "invalid_key_role_and_user_type_fields";
    public static final String INVALID_KEY_ROLE_AND_KEY_TYPE_FIELDS = "invalid_key_role_and_key_type_fields";
    public static final String INVALID_KEY_TYPE = "invalid_key_type";

    public static final String YOU_ARE_NOT_COMPANY_DIRECTOR = "you_are_not_company_director";
    public static final String PHYSICAL_PERSON_INFO_NOT_FOUND = "physical_person_info_not_found";
    public static final String REQUIRED_SERIAL_NUMBER_OR_TIN_OR_PINFL = "required_serial_number_or_tin_or_pinfl";
    public static final String AUTH_UNAUTHORIZED = "auth_unauthorized";
    public static final String AUTH_BAD_CREDENTIALS = "auth_bad_credentials";
    public static final String ERROR_WHEN_CHECKING_A_SIGNED_DOCUMENT = "error_when_checking_a_signed_document";
    public static final String INVALID_PRIVILEGE_ID_FOR_RECREATION = "invalid_privilege_id_for_recreation";
    public static final String COULD_NOT_INITIALIZE_DIR = "could_not_initialize_dir";
    public static final String PHONE_COUNT_IS_OUT_OF_LIMIT_BY_PINFL = "phone_count_is_out_of_limit_by_pinfl";
    public static final String EMAIL_COUNT_IS_OUT_OF_LIMIT_BY_PINFL = "email_count_is_out_of_limit_by_pinfl";
    public static final String INVALID_JURIDIC_PRIVILEGE = "invalid_juridic_privilege";
    public static final String INVALID_PRIVILEGE_ID_FOR_RAMS = "invalid_privilege_id_for_rams";


    public static final String CAMS_SERVER_ERROR = "cams_server_error";
    public static final String DIRECTOR_NOT_FOUND = "director_not_found";
    public static final String ACTIVE_DIRECTOR_EXISTS = "active_director_exists";
    public static final String COMPANY_POSITION_IS_ALREADY_EXISTS = "company_position_is_already_exists";

    public static final String DATA_ALREADY_EXISTS = "data_already_exists";
    public static final String INVALID_FILENAME = "invalid_filename";
    public static final String OTP_ALREADY_SENT = "otp_already_sent";
    public static final String OTP_NOT_FOUND = "otp_not_found";
    public static final String OTP_EXPIRED = "otp_expired";
    public static final String EMAIL_VERIFICATION_NOT_SUPPORTED = "email_verification_not_supported";
    public static final String EMAIL_IS_INVALID_OR_NULL = "email_is_invalid_or_null";
    public static final String CONTACT_IS_SAME_WITH_ORIGINAL_ONE = "contact_is_same_with_original_one";
    public static final String PHONE_IS_INVALID_OR_NULL = "phone_is_invalid_or_null";
    public static final String CLIENT_EXCEPTION = "client_exception";
    public static final String APPLICATION_FOR_JURIDICAL_EDS_CAN_NOT_HAVE_PRIVILEGE = "application_for_juridical_eds_can_not_have_privilege";
    public static final String APPLICATION_WAS_FOR_JURIDICAL_EDS_BUT_HAS_PRIVILEGE = "application_was_for_juridical_eds_but_has_privilege";
    public static final String PRIVILEGE_REQUESTED_WITH_JUR_TIN = "privilege_requested_with_jur_tin";
    public static final String MIGRATION_SYNC_EXCEPTION = "migration_sync_exception";
    public static final String SYNC_BILLING_DB_COMMIT_EXCEPTION = "sync_billing_db_commit_exception";
    public static final String SYNC_BILLING_DB_ROLLBACK_EXCEPTION = "sync_billing_db_rollback_exception";
    public static final String ACCMAN_BILL_TRANSACTION_ID_IS_NOT_FOUND = "accman_bill_transaction_id_is_not_found";
    public static final String MIGRATION_SYNC_INSERT_KEY_EXCEPTION = "migration_sync_insert_key_exception";
    public static final String MIGRATION_GET_SUBJECT_ID_EXCEPTION = "migration_get_subject_id_exception";
    public static final String MIGRATION_GET_SUBJECT_ID_BY_SERIALNUMBER_EXCEPTION = "migration_get_subject_id_by_serialnumber_exception";
    public static final String MIGRATION_GET_SUBJECT_ID_BY_SEQUENCE_EXCEPTION = "migration_get_subject_id_by_sequence_exception";
    public static final String MIGRATION_INSERT_SUBJECT_EXCEPTION = "migration_insert_subject_exception";
    public static final String MIGRATION_INVALID_TIN_OR_PINFL_EXCEPTION = "migration_invalid_tin_or_pinfl_exception";
    public static final String MIGRATION_APPLICATION_UPDATING_EXCEPTION = "migration_application_updating_exception";
    public static final String NOT_ENOUGH_MONEY_FOR_KEY = "not_enough_money_for_key";
    public static final String HOST_NOT_FOUND = "HOST_NOT_FOUND";
    public static final String IP_NOT_FOUND = "IP_NOT_FOUND";

    private ErrorCode() {
        throw new AssertionError("you will not get an instance of ErrorCode class");
    }
}
