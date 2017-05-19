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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Kalkulacka
{
    /// <summary>
    /// Interakční logika pro MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        double pamet;
        Operace operace;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void cislo1Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 1;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo2Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 2;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo3Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 3;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo4Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 4;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo5Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 5;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo6Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 6;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo7Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 7;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo8Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 8;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo9Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10 + 9;
            displayTextBox.Text = cislo.ToString();
        }

        private void cislo0Button_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            cislo = cislo * 10;
            displayTextBox.Text = cislo.ToString();
        }

        private void plusButton_Click(object sender, RoutedEventArgs e)
        {
            pamet = double.Parse(displayTextBox.Text);
            operace = Operace.PLUS;
            displayTextBox.Text = "0";
        }

        private void minusButton_Click(object sender, RoutedEventArgs e)
        {
            pamet = double.Parse(displayTextBox.Text);
            operace = Operace.MINUS;
            displayTextBox.Text = "0";
        }

        private void kratButton_Click(object sender, RoutedEventArgs e)
        {
            pamet = double.Parse(displayTextBox.Text);
            operace = Operace.KRAT;
            displayTextBox.Text = "0";
        }

        private void delenoButton_Click(object sender, RoutedEventArgs e)
        {
            pamet = double.Parse(displayTextBox.Text);
            operace = Operace.DELENO;
            displayTextBox.Text = "0";
        }

        private void rovnaseButton_Click(object sender, RoutedEventArgs e)
        {
            double cislo = double.Parse(displayTextBox.Text);
            switch (operace)
            {
                case Operace.PLUS:
                    pamet += cislo;
                    break;

                case Operace.MINUS:
                    pamet -= cislo;
                    break;

                case Operace.KRAT:
                    pamet *= cislo;
                    break;

                case Operace.DELENO:
                    pamet /= cislo;
                    break;

                default:
                    break;
            }

            displayTextBox.Text = pamet.ToString();
        }

        public enum Operace
        {
            PLUS,
            MINUS,
            KRAT,
            DELENO,
        }
    }
}
