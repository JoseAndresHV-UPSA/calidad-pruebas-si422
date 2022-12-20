import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_testing/screens/login_screen.dart';

void main() {
  testWidgets('El Login debería tener un título', (tester) async {
    //ARRANGE
    await tester.pumpWidget(const MaterialApp(
      home: LoginScreen(),
    ));

    //ACT
    final title = find.text('Login');

    //ASSERT
    expect(title, findsOneWidget);
  });

  testWidgets('El Login debería tener un campo de texto Email', (tester) async {
    //ARRANGE
    await tester.pumpWidget(const MaterialApp(
      home: LoginScreen(),
    ));

    //ACT
    final userEmailText = find.byKey(const ValueKey('email_id'));

    //ASSERT
    expect(userEmailText, findsOneWidget);
  });

  testWidgets('El Login debería tener un campo de texto Password',
      (tester) async {
    //ARRANGE
    await tester.pumpWidget(const MaterialApp(
      home: LoginScreen(),
    ));

    //ACT
    final userPasswordText = find.byKey(const ValueKey('password_id'));

    //ASSERT
    expect(userPasswordText, findsOneWidget);
  });

  testWidgets('El Login debería tener un botón para iniciar sesión',
      (tester) async {
    //ARRANGE
    await tester.pumpWidget(const MaterialApp(
      home: LoginScreen(),
    ));

    //ACT
    final loginButton = find.byType(ElevatedButton);

    //ASSERT
    expect(loginButton, findsOneWidget);
  });

  testWidgets('Deberia mostrar error de campo requerido',
      (WidgetTester tester) async {
    // ARRANGE
    await tester.pumpWidget(const MaterialApp(
      home: LoginScreen(),
    ));
    // ACT
    Finder loginButton = find.byType(ElevatedButton);
    await tester.tap(loginButton);
    await tester.pumpAndSettle();

    Finder error = find.text('Campo requerido');

    // ASSERT
    expect(error, findsWidgets);
  });

  testWidgets('Deberia aceptar si el email y password son correctos',
      (WidgetTester tester) async {
    // ARRANGE
    await tester.pumpWidget(const MaterialApp(
      home: LoginScreen(),
    ));

    // ACT
    Finder userTextName = find.byKey(const ValueKey('email_id'));
    Finder userTextPassword = find.byKey(const ValueKey('password_id'));

    await tester.enterText(userTextName, "jose@gmail.com");
    await tester.enterText(userTextPassword, "password");

    Finder loginButton = find.byType(ElevatedButton);
    await tester.tap(loginButton);
    await tester.pumpAndSettle();

    Finder error = find.text('Campo requerido');

    // ASSERT
    expect(error, findsNothing);
  });
}
