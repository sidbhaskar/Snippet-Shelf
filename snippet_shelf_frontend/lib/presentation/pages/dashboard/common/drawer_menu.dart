import 'package:flutter/material.dart';

import 'CustomButton.dart';

class DrawerMenu extends StatelessWidget {
  const DrawerMenu({super.key});

  @override
  Widget build(BuildContext context) {
    return Drawer(
      backgroundColor: Colors.white,
      child: Column(
        children: [
          // Create Snippet Button
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: CustomButton(
              color: Color(0xFF6366F1),
              icon: Icons.add,
              hasIcon: true,
              height: 50,
              width: 280,
              iconSize: 20,
              textSize: 14,
              text: 'Create Snippet',
              function: () {
                print('Submitted');
              },
            ),
          ),

          Expanded(
            child: ListView(
              padding: const EdgeInsets.symmetric(horizontal: 8),
              children: [
                // Main Menu Items
                _buildMenuItem(
                  icon: Icons.home_outlined,
                  title: 'Dashboard',
                  count: 24,
                  isActive: true,
                ),
                _buildMenuItem(
                  icon: Icons.favorite_outline,
                  title: 'Favorites',
                  count: 8,
                ),
                _buildMenuItem(icon: Icons.history, title: 'Recent'),
                _buildMenuItem(
                  icon: Icons.archive_outlined,
                  title: 'Archive',
                  count: 3,
                ),

                const SizedBox(height: 24),

                // Collections Section
                _buildSectionHeader('COLLECTIONS'),
                _buildCollectionItem(
                  color: Colors.red,
                  title: 'Frontend',
                  count: 12,
                ),
                _buildCollectionItem(
                  color: Colors.blue,
                  title: 'Backend',
                  count: 8,
                ),
                _buildCollectionItem(
                  color: Colors.green,
                  title: 'DevOps',
                  count: 4,
                ),
                _buildAddCollectionItem(),

                const SizedBox(height: 24),

                // Languages Section
                _buildSectionHeader('LANGUAGES'),
                _buildLanguageItem(
                  color: const Color(0xFFF7DF1E),
                  icon: Icons.javascript,
                  title: 'JavaScript',
                  count: 15,
                ),
                _buildLanguageItem(
                  color: const Color(0xFF3776AB),
                  icon: Icons.code,
                  title: 'Python',
                  count: 9,
                ),
                _buildLanguageItem(
                  color: const Color(0xFF61DAFB),
                  icon: Icons.web,
                  title: 'React',
                  count: 7,
                ),
                _buildLanguageItem(
                  color: const Color(0xFF339933),
                  icon: Icons.circle,
                  title: 'Node.js',
                  count: 5,
                ),

                const SizedBox(height: 16),

                // View all languages link
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 16),
                  child: InkWell(
                    onTap: () {},
                    child: Row(
                      children: [
                        Text(
                          'View all languages',
                          style: TextStyle(
                            color: Colors.grey[600],
                            fontSize: 14,
                          ),
                        ),
                        const SizedBox(width: 8),
                        Icon(
                          Icons.arrow_forward,
                          size: 16,
                          color: Colors.grey[600],
                        ),
                      ],
                    ),
                  ),
                ),

                const SizedBox(height: 32),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildMenuItem({
    required IconData icon,
    required String title,
    int? count,
    bool isActive = false,
  }) {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 2),
      decoration: BoxDecoration(
        color: isActive ? const Color(0xFF6366F1).withOpacity(0.1) : null,
        borderRadius: BorderRadius.circular(8),
      ),
      child: ListTile(
        leading: Icon(
          icon,
          color: isActive ? const Color(0xFF6366F1) : Colors.grey[600],
          size: 20,
        ),
        title: Text(
          title,
          style: TextStyle(
            color: isActive ? const Color(0xFF6366F1) : Colors.grey[800],
            fontSize: 14,
            fontWeight: isActive ? FontWeight.w500 : FontWeight.normal,
          ),
        ),
        trailing: count != null
            ? Container(
                padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 2),
                decoration: BoxDecoration(
                  color: isActive ? const Color(0xFF6366F1) : Colors.grey[300],
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Text(
                  '$count',
                  style: TextStyle(
                    color: isActive ? Colors.white : Colors.grey[600],
                    fontSize: 12,
                    fontWeight: FontWeight.w500,
                  ),
                ),
              )
            : null,
        onTap: () {},
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 0),
        dense: true,
        visualDensity: VisualDensity.compact,
      ),
    );
  }

  Widget _buildSectionHeader(String title) {
    return Padding(
      padding: const EdgeInsets.fromLTRB(16, 0, 16, 8),
      child: Text(
        title,
        style: TextStyle(
          color: Colors.grey[500],
          fontSize: 12,
          fontWeight: FontWeight.w600,
          letterSpacing: 0.5,
        ),
      ),
    );
  }

  Widget _buildCollectionItem({
    required Color color,
    required String title,
    required int count,
  }) {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 2),
      child: ListTile(
        leading: Container(
          width: 8,
          height: 8,
          decoration: BoxDecoration(color: color, shape: BoxShape.circle),
        ),
        title: Text(
          title,
          style: TextStyle(color: Colors.grey[800], fontSize: 14),
        ),
        trailing: Text(
          '$count',
          style: TextStyle(color: Colors.grey[500], fontSize: 12),
        ),
        onTap: () {},
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 0),
        dense: true,
        visualDensity: VisualDensity.compact,
      ),
    );
  }

  Widget _buildAddCollectionItem() {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 2),
      child: ListTile(
        leading: Icon(Icons.add, color: Colors.grey[600], size: 16),
        title: Text(
          'New Collection',
          style: TextStyle(color: Colors.grey[600], fontSize: 14),
        ),
        onTap: () {},
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 0),
        dense: true,
        visualDensity: VisualDensity.compact,
      ),
    );
  }

  Widget _buildLanguageItem({
    required Color color,
    required IconData icon,
    required String title,
    required int count,
  }) {
    return Container(
      margin: const EdgeInsets.symmetric(vertical: 2),
      child: ListTile(
        leading: Container(
          width: 20,
          height: 20,
          decoration: BoxDecoration(
            color: color,
            borderRadius: BorderRadius.circular(4),
          ),
          child: Icon(
            icon,
            color: title == 'JavaScript' ? Colors.black : Colors.white,
            size: 12,
          ),
        ),
        title: Text(
          title,
          style: TextStyle(color: Colors.grey[800], fontSize: 14),
        ),
        trailing: Text(
          '$count',
          style: TextStyle(color: Colors.grey[500], fontSize: 12),
        ),
        onTap: () {},
        contentPadding: const EdgeInsets.symmetric(horizontal: 16, vertical: 0),
        dense: true,
        visualDensity: VisualDensity.compact,
      ),
    );
  }
}
