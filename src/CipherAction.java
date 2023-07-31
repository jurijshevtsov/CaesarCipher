public enum CipherAction {
    ENCRYPT, DECRYPT;

    public static CipherAction fromString(String action) {
        try {
            return CipherAction.valueOf(action.toUpperCase());
        } catch (Exception ex) {
            return null;
        }
    }
}