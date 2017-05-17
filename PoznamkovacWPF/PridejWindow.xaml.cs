using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace PoznamkovacWPF
{
    /// <summary>
    /// Interakční logika pro PridejWindow.xaml
    /// </summary>
    public partial class PridejWindow : Window
    {
        private Databaze databaze;

        public PridejWindow(Databaze databaze)
        {
            InitializeComponent();
            this.databaze = databaze;
        }

        private void pridatButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                databaze.PridejZaznam(datumDatePicker.SelectedDate, zaznamTextBox.Text);
                Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Chyba", MessageBoxButton.OK, MessageBoxImage.Exclamation);
            }
        }

        private void zrusitButton_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}
