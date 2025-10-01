import 'package:flutter/material.dart';
import 'package:snippet_shelf_frontend/presentation/pages/dashboard/common/CustomButton.dart';
import 'package:snippet_shelf_frontend/presentation/pages/dashboard/common/app_bar_custom.dart';
import 'package:snippet_shelf_frontend/presentation/pages/dashboard/common/drawer_menu.dart';

class Dashboard extends StatefulWidget {
  const Dashboard({super.key});

  @override
  State<Dashboard> createState() => _DashboardState();
}

class _DashboardState extends State<Dashboard> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const AppBarCustom(),
      body: Row(
        children: [
          const DrawerMenu(),
          Expanded(
            child: Container(
              color: Colors.grey[50],
              padding: const EdgeInsets.all(24),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Header Section
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const Text(
                            'Welcome back, John!',
                            style: TextStyle(
                              fontSize: 28,
                              fontWeight: FontWeight.bold,
                              color: Colors.black87,
                            ),
                          ),
                          const SizedBox(height: 8),
                          Text(
                            'Manage and organize your code snippets efficiently',
                            style: TextStyle(
                              fontSize: 16,
                              color: Colors.grey[600],
                            ),
                          ),
                        ],
                      ),
                      Row(
                        children: [
                          OutlinedButton.icon(
                            onPressed: () {},
                            icon: const Icon(Icons.download_outlined, size: 18),
                            label: const Text('Export'),
                            style: OutlinedButton.styleFrom(
                              foregroundColor: Colors.grey[700],
                              side: BorderSide(color: Colors.grey[300]!),
                              padding: const EdgeInsets.symmetric(
                                horizontal: 16,
                                vertical: 12,
                              ),
                              shape: const RoundedRectangleBorder(
                                borderRadius: BorderRadius.all(
                                  Radius.circular(8),
                                ),
                              ),
                            ),
                          ),

                          const SizedBox(width: 12),
                          CustomButton(
                            function: () {},
                            text: "New Snippet",
                            height: 30,
                            width: 120,
                            padding: 5,
                            icon: Icons.add,
                            hasIcon: true,
                            iconSize: 14,
                            textSize: 12,
                          ),
                          // ElevatedButton.icon(
                          //   onPressed: () {},
                          //   icon: const Icon(Icons.add, size: 18),
                          //   label: const Text('New Snippet'),
                          //   style: ElevatedButton.styleFrom(
                          //     backgroundColor: const Color(0xFF6366F1),
                          //     foregroundColor: Colors.white,
                          //     elevation: 0,
                          //     padding: const EdgeInsets.symmetric(
                          //       horizontal: 16,
                          //       vertical: 12,
                          //     ),
                          //     shape: RoundedRectangleBorder(
                          //       borderRadius: BorderRadius.circular(8),
                          //     ),
                          //   ),
                          // ),
                        ],
                      ),
                    ],
                  ),

                  const SizedBox(height: 32),

                  // Stats Cards
                  Row(
                    children: [
                      Expanded(
                        child: _buildStatsCard(
                          title: 'Total Snippets',
                          value: '124',
                          icon: Icons.code_outlined,
                          iconColor: const Color(0xFF6366F1),
                          iconBgColor: const Color(0xFF6366F1).withOpacity(0.1),
                          subtitle: '+12% from last month',
                          subtitleColor: Colors.green,
                        ),
                      ),
                      const SizedBox(width: 16),
                      Expanded(
                        child: _buildStatsCard(
                          title: 'Collections',
                          value: '8',
                          icon: Icons.folder_outlined,
                          iconColor: const Color(0xFF8B5CF6),
                          iconBgColor: const Color(0xFF8B5CF6).withOpacity(0.1),
                          subtitle: '+2 new this week',
                          subtitleColor: Colors.green,
                        ),
                      ),
                      const SizedBox(width: 16),
                      Expanded(
                        child: _buildStatsCard(
                          title: 'Languages',
                          value: '15',
                          icon: Icons.language_outlined,
                          iconColor: const Color(0xFF06B6D4),
                          iconBgColor: const Color(0xFF06B6D4).withOpacity(0.1),
                          subtitle: 'JavaScript, Python, React...',
                          subtitleColor: Colors.grey[600]!,
                        ),
                      ),
                      const SizedBox(width: 16),
                      Expanded(
                        child: _buildStatsCard(
                          title: 'Favorites',
                          value: '23',
                          icon: Icons.star_outline,
                          iconColor: const Color(0xFFF59E0B),
                          iconBgColor: const Color(0xFFF59E0B).withOpacity(0.1),
                          subtitle: 'Most used snippets',
                          subtitleColor: Colors.grey[600]!,
                        ),
                      ),
                    ],
                  ),

                  const SizedBox(height: 32),

                  //todo : recent snippets
                  // Additional content area (can be expanded for recent snippets, etc.)
                  Expanded(
                    child: Container(
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(12),
                        border: Border.all(color: Colors.grey[200]!),
                      ),
                      padding: const EdgeInsets.all(24),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                'Recent Snippets',
                                style: TextStyle(
                                  fontSize: 18,
                                  fontWeight: FontWeight.w600,
                                  color: Colors.black87,
                                ),
                              ),
                              TextButton(
                                onPressed: () {},
                                child: const Text(
                                  'View all',
                                  style: TextStyle(
                                    color: Color(0xFF6366F1),
                                    fontSize: 14,
                                  ),
                                ),
                              ),
                            ],
                          ),

                          const SizedBox(height: 16),
                          Expanded(
                            child: Center(
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  Container(
                                    padding: const EdgeInsets.all(16),
                                    decoration: BoxDecoration(
                                      color: Colors.grey[100],
                                      shape: BoxShape.circle,
                                    ),
                                    child: Icon(
                                      Icons.code,
                                      size: 32,
                                      color: Colors.grey[400],
                                    ),
                                  ),
                                  const SizedBox(height: 16),
                                  Text(
                                    'Your recent snippets will appear here',
                                    style: TextStyle(
                                      color: Colors.grey[600],
                                      fontSize: 16,
                                    ),
                                  ),
                                  const SizedBox(height: 8),
                                  Text(
                                    'Create your first snippet to get started',
                                    style: TextStyle(
                                      color: Colors.grey[500],
                                      fontSize: 14,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

Widget _buildStatsCard({
  required String title,
  required String value,
  required IconData icon,
  required Color iconColor,
  required Color iconBgColor,
  required String subtitle,
  required Color subtitleColor,
}) {
  return Container(
    padding: const EdgeInsets.all(20),
    decoration: BoxDecoration(
      color: Colors.white,
      borderRadius: BorderRadius.circular(12),
      border: Border.all(color: Colors.grey[200]!),
      boxShadow: [
        BoxShadow(
          color: Colors.black.withOpacity(0.04),
          blurRadius: 8,
          offset: const Offset(0, 2),
        ),
      ],
    ),
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              title,
              style: TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.w500,
                color: Colors.grey[600],
              ),
            ),
            Container(
              padding: const EdgeInsets.all(8),
              decoration: BoxDecoration(
                color: iconBgColor,
                borderRadius: BorderRadius.circular(8),
              ),
              child: Icon(icon, color: iconColor, size: 20),
            ),
          ],
        ),
        const SizedBox(height: 12),
        Text(
          value,
          style: const TextStyle(
            fontSize: 32,
            fontWeight: FontWeight.bold,
            color: Colors.black87,
          ),
        ),
        const SizedBox(height: 8),
        Text(
          subtitle,
          style: TextStyle(
            fontSize: 12,
            color: subtitleColor,
            fontWeight: FontWeight.w500,
          ),
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
        ),
      ],
    ),
  );
}
