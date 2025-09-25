import 'package:flutter/material.dart';

class CustomButton extends StatelessWidget {
  final double height;
  final double width;
  final Color color;
  final String text;
  final double iconSize;
  final double textSize;
  final double padding;
  final IconData icon;
  final bool hasIcon;
  final VoidCallback function;

  const CustomButton({
    super.key,
    this.height = 48.0,
    this.padding = 16.0,
    this.width = double.infinity,
    this.color = const Color(0xFF6366F1),
    this.icon = Icons.add,
    this.hasIcon = true,
    required this.function,
    required this.text,
    required this.iconSize,
    required this.textSize,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: width,
      height: height,
      child: ElevatedButton(
        onPressed: function,
        style: ElevatedButton.styleFrom(
          backgroundColor: color,
          foregroundColor: Colors.white,
          elevation: 0,
          padding: EdgeInsets.symmetric(
            horizontal: padding,
          ), // Internal padding
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.min, // Important!
          children: [
            if (hasIcon) ...[
              Icon(icon, size: iconSize),
              const SizedBox(width: 8),
            ],
            Flexible(
              // Prevent overflow by wrapping Text
              child: Text(
                text,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                  fontSize: textSize,
                  fontWeight: FontWeight.w500,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
