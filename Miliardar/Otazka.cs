using System;

namespace Miliardar
{
	public class Otazka
	{
		public enum Odpoved { Prvni, Druha, Treti, Ctvrta };

		public string Text {get; private set;}
		public string PrvniOdpoved {get; private set;}
		public string DruhaOdpoved {get; private set;}
		public string TretiOdpoved {get; private set;}
		public string CtvrtaOdpoved {get; private set;}
		public Odpoved Spravna {get; private set;}

		public Otazka (string text, string prvni, string druha, string treti, string ctvrta, Odpoved spravna)
		{
			Text = text;
			PrvniOdpoved = prvni;
			DruhaOdpoved = druha;
			TretiOdpoved = treti;
			CtvrtaOdpoved = ctvrta;
			Spravna = spravna;
		}

		public override string ToString()
		{
			return 	Text 
				  	+ System.Environment.NewLine + "1) " + PrvniOdpoved 
				  	+ System.Environment.NewLine + "2) " + DruhaOdpoved
					+ System.Environment.NewLine + "3) " + TretiOdpoved 
					+ System.Environment.NewLine + "4) " + CtvrtaOdpoved;
		}
	}
}

