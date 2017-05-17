using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PoznamkovacWPF
{
    public class Zaznam : IComparable<Zaznam>
    {
        public DateTime DatumCas { get; set; }
        public String Text { get; set; }

        public Zaznam(DateTime datumCas, String text)
        {
            DatumCas = datumCas;
            Text = text;
        }

        public override string ToString()
        {
            return DatumCas.Date + ": " + Text;
        }

        public int CompareTo(Zaznam z)
        {
            return DatumCas.CompareTo(z.DatumCas);
        }
    }
}
