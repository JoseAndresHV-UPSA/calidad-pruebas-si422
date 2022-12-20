import 'package:flutter_test/flutter_test.dart';
import 'package:integration_testing/models/validator.dart';

void main() {
  group('Email Validator', () {
    test('Email no debe ser nulo o vacio', () {
      // ARRANGE && ACT
      var resultado = Validator.validateEmail('');

      // ASSERT
      expect(resultado, 'Campo requerido');
    });

    test('Email validado', () {
      // ARRANGE && ACT
      var resultado = Validator.validateEmail('aaaa@gmail.com');

      // ASSERT
      expect(resultado, null);
    });
  });

  group('Password Validator', () {
    test('Password no debe ser nula o vacia', () {
      // ARRANGE && ACT
      var resultado = Validator.validatePassword('');

      // ASSERT
      expect(resultado, 'Campo requerido');
    });

    test('Password validada', () {
      // ARRANGE && ACT
      var resultado = Validator.validatePassword('abcdefg');

      // ASSERT
      expect(resultado, null);
    });
  });
}
