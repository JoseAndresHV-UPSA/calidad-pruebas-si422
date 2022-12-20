import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import 'package:integration_testing/screens/login_screen.dart';

main() {
  group("Test de Loging", () {
    IntegrationTestWidgetsFlutterBinding.ensureInitialized();
    testWidgets(
        "Debería ir al Menu Principal cuando el usuario presione Login y haya"
        "registrado un email y password válidos", (WidgetTester tester) async {
      //ARRANGE
      await tester.pumpWidget(const MaterialApp(
        home: LoginScreen(),
      ));
      //ACT
      Finder userNameTextField = find.byKey(const ValueKey('email_id'));
      Finder passwordTextField = find.byKey(const ValueKey('password'));

      await tester.enterText(userNameTextField, "tito@gmail.com");
      await tester.enterText(passwordTextField, "password");

      Finder loginBotton = find.byType(ElevatedButton);
      await tester.tap(loginBotton);
      await tester.pumpAndSettle(const Duration(seconds: 5));

      Finder welcomeText = find.byType(Text);
      //ASSERT
      expect(welcomeText, findsOneWidget);
    });

    testWidgets(
        "Debería ir al Menu Principal cuando el usuario presione Login y haya"
        "registrado un email y password válidos", (WidgetTester tester) async {
      //ARRANGE
      await tester.pumpWidget(const MaterialApp(
        home: LoginScreen(),
      ));
      //ACT
      Finder userNameTextField = find.byKey(const ValueKey('email_id'));
      Finder passwordTextField = find.byKey(const ValueKey('password'));

      await tester.enterText(userNameTextField, "tito@gmail.com");
      await tester.enterText(passwordTextField, "password");

      Finder loginBotton = find.byType(ElevatedButton);
      await tester.tap(loginBotton);
      await tester.pumpAndSettle(const Duration(seconds: 5));

      Finder welcomeText = find.byType(Text);
      //ASSERT
      expect(welcomeText, findsOneWidget);
    });
  });
}
