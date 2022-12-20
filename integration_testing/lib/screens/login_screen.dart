import 'package:flutter/material.dart';
import '../models/validator.dart';
import 'home_screen.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final GlobalKey<FormState> _key = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Login'),
        ),
        body: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Form(
            key: _key,
            child: Column(
              children: <Widget>[
                TextFormField(
                  controller: _emailController,
                  key: const ValueKey('email_id'),
                  decoration:
                      const InputDecoration(hintText: 'Ingrese el mail'),
                  validator: (valor) => Validator.validateEmail(valor ?? ""),
                ),
                TextFormField(
                  controller: _passwordController,
                  key: const ValueKey('password'),
                  decoration:
                      const InputDecoration(hintText: 'Ingrese el password'),
                  validator: (valor) => Validator.validatePassword(valor ?? ""),
                ),
                ElevatedButton(
                    onPressed: () {
                      if (_key.currentState?.validate() == true) {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => const HomeScreen(),
                            ));
                      }
                    },
                    child: const Text('Login')),
              ],
            ),
          ),
        ));
  }
}
