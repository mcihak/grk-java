using System;

namespace Miliardar
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			SeznamOtazek otazky = new SeznamOtazek();
			otazky.Pridej ("Jakou barvu má tráva?", "červenou", "zelenou", "modrou", "bílou", Otazka.Odpoved.Druha);
			otazky.Pridej ("Kolik nohou má pavouk?", "dvě", "čtyři", "šest", "osm", Otazka.Odpoved.Ctvrta);
			otazky.Pridej ("Kdo vyhrál ligu mistrů v sezńě 2016/2017?", "Bayern Mnichov", "FC Barcelona", "Real Madrid", "Juventus Turín", Otazka.Odpoved.Treti);

			//Console.Write (otazky.PrectiOtazku());

			//otazky.Vypis ();

			do {
				Console.Write (otazky.PrectiOtazku ());
			} while (otazky.PosunSeNaDalsi ());
		}
	}
}
