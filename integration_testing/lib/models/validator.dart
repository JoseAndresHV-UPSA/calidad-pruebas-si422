class Validator {
  static String? validateEmail(String email) {
    if (email.isEmpty) {
      return 'Campo requerido';
    }

    String pattern =
        r"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]"
        r"{0,253}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]"
        r"{0,253}[a-zA-Z0-9])?)*$";

    RegExp regExp = RegExp(pattern);
    if (!regExp.hasMatch(email)) {
      return 'Introduzca un email valido';
    }

    return null;
  }

  static String? validatePassword(String password) {
    if (password.isEmpty) {
      return 'Campo requerido';
    }

    String pattern = r"[a-zA-Z@$]{6,10}$";
    RegExp regExp = RegExp(pattern);

    if (!regExp.hasMatch(password)) {
      return 'Password incorrecto';
    }

    return null;
  }
}
