/**
 * 版权所有归属: xxx 公司 [2006 ~ 2014]
 * 本代码开源使用，如需要复制、修改或用作它途，请指明出处，
 */
package com.df.generate.impl;

import com.df.generate.AbstractGenerator;

/**
 * 英文名称生成器
 * 
 * @author yejf
 * @date 2014-3-12 上午10:13:29
 * @since JDK6.0
 * @version 1.0
 * @description TODO
 */
public class EnglishNameGenerator extends AbstractGenerator {

	/** 后缀 */
	private static String[] suffixes = { "II", "III", "Phd", "Jr", "Sr" };
	
	/** 前缀*/
	private static String[] prefixes = { "Mr", "Mrs", "Ms" };
	
	/*** 前名 ****/
	private static String[] firstNames = { "Aaron", "Abby", "Abigail", "Adam",
			"Alan", "Albert", "Alex", "Alexandra", "Alexis", "Alice", "Alicia",
			"Alisha", "Alissa", "Allen", "Allison", "Alyssa", "Amanda",
			"Amber", "Amy", "Andrea", "Andrew", "Andy", "Angel", "Angela",
			"Angie", "Anita", "Ann", "Anna", "Annette", "Anthony", "Antonio",
			"April", "Arthur", "Ashley", "Audrey", "Austin", "Autumn", "Baby",
			"Barb", "Barbara", "Becky", "Benjamin", "Beth", "Bethany", "Betty",
			"Beverly", "Bill", "Billie", "Billy", "Blake", "Bob", "Bobbie",
			"Bobby", "Bonnie", "Brad", "Bradley", "Brady", "Brandi", "Brandon",
			"Brandy", "Breanna", "Brenda", "Brent", "Brett", "Brian",
			"Brianna", "Brittany", "Brooke", "Brooklyn", "Bruce", "Bryan",
			"Caleb", "Cameron", "Candy", "Carl", "Carla", "Carmen", "Carol",
			"Carolyn", "Carrie", "Casey", "Cassandra", "Catherine", "Cathy",
			"Chad", "Charlene", "Charles", "Charlie", "Charlotte", "Chase",
			"Chasity", "Chastity", "Chelsea", "Cheryl", "Chester", "Cheyenne",
			"Chris", "Christian", "Christina", "Christine", "Christoph",
			"Christopher", "Christy", "Chuck", "Cindy", "Clara", "Clarence",
			"Clayton", "Clifford", "Clint", "Cody", "Colton", "Connie",
			"Corey", "Cory", "Courtney", "Craig", "Crystal", "Curtis",
			"Cynthia", "Dakota", "Dale", "Dallas", "Dalton", "Dan", "Dana",
			"Daniel", "Danielle", "Danny", "Darla", "Darlene", "Darrell",
			"Darren", "Dave", "David", "Dawn", "Dean", "Deanna", "Debbie",
			"Deborah", "Debra", "Denise", "Dennis", "Derek", "Derrick",
			"Destiny", "Devin", "Diana", "Diane", "Dillon", "Dixie", "Dominic",
			"Don", "Donald", "Donna", "Donnie", "Doris", "Dorothy", "Doug",
			"Douglas", "Drew", "Duane", "Dustin", "Dusty", "Dylan", "Earl",
			"Ed", "Eddie", "Edward", "Elaine", "Elizabeth", "Ellen", "Emily",
			"Eric", "Erica", "Erika", "Erin", "Ernest", "Ethan", "Eugene",
			"Eva", "Evelyn", "Everett", "Faith", "Father", "Felicia", "Floyd",
			"Francis", "Frank", "Fred", "Gabriel", "Gage", "Gail", "Gary",
			"Gene", "George", "Gerald", "Gina", "Ginger", "Glen", "Glenn",
			"Gloria", "Grace", "Greg", "Gregory", "Haley", "Hannah", "Harley",
			"Harold", "Harry", "Heath", "Heather", "Heidi", "Helen", "Herbert",
			"Holly", "Hope", "Howard", "Hunter", "Ian", "Isaac", "Jack",
			"Jackie", "Jacob", "Jade", "Jake", "James", "Jamie", "Jan", "Jane",
			"Janet", "Janice", "Jared", "Jasmine", "Jason", "Jay", "Jean",
			"Jeannie", "Jeff", "Jeffery", "Jeffrey", "Jenna", "Jennifer",
			"Jenny", "Jeremiah", "Jeremy", "Jerry", "Jesse", "Jessica",
			"Jessie", "Jill", "Jim", "Jimmy", "Joann", "Joanne", "Jodi",
			"Jody", "Joe", "Joel", "Joey", "John", "Johnathan", "Johnny",
			"Jon", "Jonathan", "Jonathon", "Jordan", "Joseph", "Josh",
			"Joshua", "Joyce", "Juanita", "Judy", "Julia", "Julie", "Justin",
			"Kaitlyn", "Karen", "Katelyn", "Katherine", "Kathleen", "Kathryn",
			"Kathy", "Katie", "Katrina", "Kay", "Kayla", "Kaylee", "Keith",
			"Kelly", "Kelsey", "Ken", "Kendra", "Kenneth", "Kenny", "Kevin",
			"Kim", "Kimberly", "Kris", "Krista", "Kristen", "Kristin",
			"Kristina", "Kristy", "Kyle", "Kylie", "Lacey", "Laken", "Lance",
			"Larry", "Laura", "Lawrence", "Leah", "Lee", "Leonard", "Leroy",
			"Leslie", "Levi", "Lewis", "Linda", "Lindsay", "Lindsey", "Lisa",
			"Lloyd", "Logan", "Lois", "Loretta", "Lori", "Louis", "Lynn",
			"Madison", "Mandy", "Marcus", "Margaret", "Maria", "Mariah",
			"Marie", "Marilyn", "Marion", "Mark", "Marlene", "Marsha",
			"Martha", "Martin", "Marty", "Marvin", "Mary", "Mary ann", "Mason",
			"Matt", "Matthew", "Max", "Megan", "Melanie", "Melinda", "Melissa",
			"Melody", "Michael", "Michelle", "Mickey", "Mike", "Mindy",
			"Miranda", "Misty", "Mitchell", "Molly", "Monica", "Morgan",
			"Mother", "Myron", "Nancy", "Natasha", "Nathan", "Nicholas",
			"Nick", "Nicole", "Nina", "Noah", "Norma", "Norman", "Olivia",
			"Paige", "Pam", "Pamela", "Pat", "Patricia", "Patrick", "Patty",
			"Paul", "Paula", "Peggy", "Penny", "Pete", "Phillip", "Phyllis",
			"Rachael", "Rachel", "Ralph", "Randall", "Randi", "Randy", "Ray",
			"Raymond", "Rebecca", "Regina", "Renee", "Rex", "Rhonda",
			"Richard", "Rick", "Ricky", "Rita", "Rob", "Robbie", "Robert",
			"Roberta", "Robin", "Rochelle", "Rocky", "Rod", "Rodney", "Roger",
			"Ron", "Ronald", "Ronda", "Ronnie", "Rose", "Roxanne", "Roy",
			"Russ", "Russell", "Rusty", "Ruth", "Ryan", "Sabrina", "Sally",
			"Sam", "Samantha", "Samuel", "Sandra", "Sandy", "Sara", "Sarah",
			"Savannah", "Scott", "Sean", "Seth", "Shanda", "Shane", "Shanna",
			"Shannon", "Sharon", "Shaun", "Shawn", "Shawna", "Sheila",
			"Shelly", "Sher", "Sherri", "Sherry", "Shirley", "Sierra",
			"Skyler", "Stacey", "Stacy", "Stanley", "Stephanie", "Stephen",
			"Steve", "Steven", "Sue", "Summer", "Susan", "Sydney", "Tabatha",
			"Tabitha", "Tamara", "Tammy", "Tara", "Tasha", "Tashia", "Taylor",
			"Ted", "Teresa", "Terri", "Terry", "Tessa", "Thelma", "Theresa",
			"Thomas", "Tia", "Tiffany", "Tim", "Timmy", "Timothy", "Tina",
			"Todd", "Tom", "Tommy", "Toni", "Tony", "Tonya", "Tracey",
			"Tracie", "Tracy", "Travis", "Trent", "Trevor", "Trey", "Trisha",
			"Tristan", "Troy", "Tyler", "Tyrone", "Unborn", "Valerie",
			"Vanessa", "Vernon", "Veronica", "Vicki", "Vickie", "Vicky",
			"Victor", "Victoria", "Vincent", "Virginia", "Vivian", "Walter",
			"Wanda", "Wayne", "Wendy", "Wesley", "Whitney", "William",
			"Willie", "Wyatt", "Zachary" };

	/** 后名*/
	private static String[] lastNames = { "Abbott", "Acevedo", "Acosta",
			"Adams", "Adkins", "Aguilar", "Aguirre", "Albert", "Alexander",
			"Alford", "Allen", "Allison", "Alston", "Alvarado", "Alvarez",
			"Anderson", "Andrews", "Anthony", "Armstrong", "Arnold", "Ashley",
			"Atkins", "Atkinson", "Austin", "Avery", "Avila", "Ayala", "Ayers",
			"Bailey", "Baird", "Baker", "Baldwin", "Ball", "Ballard", "Banks",
			"Barber", "Smith", "Johnson", "Williams", "Jones", "Brown",
			"Davis", "Miller", "Wilson", "Moore", "Taylor", "Thomas",
			"Jackson", "Barker", "Barlow", "Barnes", "Barnett", "Barr",
			"Barrera", "Barrett", "Barron", "Barry", "Bartlett", "Barton",
			"Bass", "Bates", "Battle", "Bauer", "Baxter", "Beach", "Bean",
			"Beard", "Beasley", "Beck", "Becker", "Bell", "Bender", "Benjamin",
			"Bennett", "Benson", "Bentley", "Benton", "Berg", "Berger",
			"Bernard", "Berry", "Best", "Bird", "Bishop", "Black", "Blackburn",
			"Blackwell", "Blair", "Blake", "Blanchard", "Blankenship",
			"Blevins", "Bolton", "Bond", "Bonner", "Booker", "Boone", "Booth",
			"Bowen", "Bowers", "Bowman", "Boyd", "Boyer", "Boyle", "Bradford",
			"Bradley", "Bradshaw", "Brady", "Branch", "Bray", "Brennan",
			"Brewer", "Bridges", "Briggs", "Bright", "Britt", "Brock",
			"Brooks", "Browning", "Bruce", "Bryan", "Bryant", "Buchanan",
			"Buck", "Buckley", "Buckner", "Bullock", "Burch", "Burgess",
			"Burke", "Burks", "Burnett", "Burns", "Burris", "Burt", "Burton",
			"Bush", "Butler", "Byers", "Byrd", "Cabrera", "Cain", "Calderon",
			"Caldwell", "Calhoun", "Callahan", "Camacho", "Cameron",
			"Campbell", "Campos", "Cannon", "Cantrell", "Cantu", "Cardenas",
			"Carey", "Carlson", "Carney", "Carpenter", "Carr", "Carrillo",
			"Carroll", "Carson", "Carter", "Carver", "Case", "Casey", "Cash",
			"Castaneda", "Castillo", "Castro", "Cervantes", "Chambers", "Chan",
			"Chandler", "Chaney", "Chang", "Chapman", "Charles", "Chase",
			"Chavez", "Chen", "Cherry", "Christensen", "Christian", "Church",
			"Clark", "Clarke", "Clay", "Clayton", "Clements", "Clemons",
			"Cleveland", "Cline", "Cobb", "Cochran", "Coffey", "Cohen", "Cole",
			"Coleman", "Collier", "Collins", "Colon", "Combs", "Compton",
			"Conley", "Conner", "Conrad", "Contreras", "Conway", "Cook",
			"Cooke", "Cooley", "Cooper", "Copeland", "Cortez", "Cote",
			"Cotton", "Cox", "Craft", "Craig", "Crane", "Crawford", "Crosby",
			"Cross", "Cruz", "Cummings", "Cunningham", "Curry", "Curtis",
			"Dale", "Dalton", "Daniel", "Daniels", "Daugherty", "Davenport",
			"David", "Davidson", "Dawson", "Day", "Dean", "Decker", "Dejesus",
			"Delacruz", "Delaney", "Deleon", "Delgado", "Dennis", "Diaz",
			"Dickerson", "Dickinson", "Dillard", "Dillon", "Dixon", "Dodson",
			"Dominguez", "Donaldson", "Donovan", "Dorsey", "Dotson", "Douglas",
			"Downs", "Doyle", "Drake", "Dudley", "Duffy", "Duke", "Duncan",
			"Dunlap", "Dunn", "Duran", "Durham", "Dyer", "Eaton", "Edwards",
			"Elliott", "Ellis", "Ellison", "Emerson", "England", "English",
			"Erickson", "Espinoza", "Estes", "Estrada", "Evans", "Everett",
			"Ewing", "Farley", "Farmer", "Farrell", "Faulkner", "Ferguson",
			"Fernandez", "Ferrell", "Fields", "Figueroa", "Finch", "Finley",
			"Fischer", "Fisher", "Fitzgerald", "Fitzpatrick", "Fleming",
			"Fletcher", "Flores", "Flowers", "Floyd", "Flynn", "Foley",
			"Forbes", "Ford", "Foreman", "Foster", "Fowler", "Fox", "Francis",
			"Franco", "Frank", "Franklin", "Franks", "Frazier", "Frederick",
			"Freeman", "French", "Frost", "Fry", "Frye", "Fuentes", "Fuller",
			"Fulton", "Gaines", "Gallagher", "Gallegos", "Galloway", "Gamble",
			"Garcia", "Gardner", "Garner", "Garrett", "Garrison", "Garza",
			"Gates", "Gay", "Gentry", "George", "Gibbs", "Gibson", "Gilbert",
			"Giles", "Gill", "Gillespie", "Gilliam", "Gilmore", "Glass",
			"Glenn", "Glover", "Goff", "Golden", "Gomez", "Gonzales",
			"Gonzalez", "Good", "Goodman", "Goodwin", "Gordon", "Gould",
			"Graham", "Grant", "Graves", "Gray", "Green", "Greene", "Greer",
			"Gregory", "Griffin", "Griffith", "Grimes", "Gross", "Guerra",
			"Guerrero", "Guthrie", "Gutierrez", "Guy", "Guzman", "Hahn",
			"Hale", "Haley", "Hall", "Hamilton", "Hammond", "Hampton",
			"Hancock", "Haney", "Hansen", "Hanson", "Hardin", "Harding",
			"Hardy", "Harmon", "Harper", "Harris", "Harrington", "Harrison",
			"Hart", "Hartman", "Harvey", "Hatfield", "Hawkins", "Hayden",
			"Hayes", "Haynes", "Hays", "Head", "Heath", "Hebert", "Henderson",
			"Hendricks", "Hendrix", "Henry", "Hensley", "Henson", "Herman",
			"Hernandez", "Herrera", "Herring", "Hess", "Hester", "Hewitt",
			"Hickman", "Hicks", "Higgins", "Hill", "Hines", "Hinton", "Hobbs",
			"Hodge", "Hodges", "Hoffman", "Hogan", "Holcomb", "Holden",
			"Holder", "Holland", "Holloway", "Holman", "Holmes", "Holt",
			"Hood", "Hooper", "Hoover", "Hopkins", "Hopper", "Horn", "Horne",
			"Horton", "House", "Houston", "Howard", "Howe", "Howell",
			"Hubbard", "Huber", "Hudson", "Huff", "Huffman", "Hughes", "Hull",
			"Humphrey", "Hunt", "Hunter", "Hurley", "Hurst", "Hutchinson",
			"Hyde", "Ingram", "Irwin", "Jacobs", "Jacobson", "James", "Jarvis",
			"Jefferson", "Jenkins", "Jennings", "Jensen", "Jimenez", "Johns",
			"Johnston", "Jordan", "Joseph", "Joyce", "Joyner", "Juarez",
			"Justice", "Kane", "Kaufman", "Keith", "Keller", "Kelley", "Kelly",
			"Kemp", "Kennedy", "Kent", "Kerr", "Key", "Kidd", "Kim", "King",
			"Kinney", "Kirby", "Kirk", "Kirkland", "Klein", "Kline", "Knapp",
			"Knight", "Knowles", "Knox", "Koch", "Kramer", "Lamb", "Lambert",
			"Lancaster", "Landry", "Lane", "Lang", "Langley", "Lara", "Larsen",
			"Larson", "Lawrence", "Lawson", "Le", "Leach", "Leblanc", "Lee",
			"Leon", "Leonard", "Lester", "Levine", "Levy", "Lewis", "Lindsay",
			"Lindsey", "Little", "Livingston", "Lloyd", "Logan", "Long",
			"Lopez", "Lott", "Love", "Lowe", "Lowery", "Lucas", "Luna",
			"Lynch", "Lynn", "Lyons", "Macdonald", "Macias", "Mack", "Madden",
			"Maddox", "Maldonado", "Malone", "Mann", "Manning", "Marks",
			"Marquez", "Marsh", "Marshall", "Martin", "Martinez", "Mason",
			"Massey", "Mathews", "Mathis", "Matthews", "Maxwell", "May",
			"Mayer", "Maynard", "Mayo", "Mays", "McBride", "McCall",
			"McCarthy", "McCarty", "McClain", "McClure", "McConnell",
			"McCormick", "McCoy", "McCray", "McCullough", "McDaniel",
			"McDonald", "McDowell", "McFadden", "McFarland", "McGee",
			"McGowan", "McGuire", "McIntosh", "McIntyre", "McKay", "McKee",
			"McKenzie", "McKinney", "McKnight", "McLaughlin", "McLean",
			"McLeod", "McMahon", "McMillan", "McNeil", "McPherson", "Meadows",
			"Medina", "Mejia", "Melendez", "Melton", "Mendez", "Mendoza",
			"Mercado", "Mercer", "Merrill", "Merritt", "Meyer", "Meyers",
			"Michael", "Middleton", "Miles", "Mills", "Miranda", "Mitchell",
			"Molina", "Monroe", "Montgomery", "Montoya", "Moody", "Moon",
			"Mooney", "Morales", "Moran", "Moreno", "Morgan", "Morin",
			"Morris", "Morrison", "Morrow", "Morse", "Morton", "Moses",
			"Mosley", "Moss", "Mueller", "Mullen", "Mullins", "Munoz",
			"Murphy", "Murray", "Myers", "Nash", "Navarro", "Neal", "Nelson",
			"Newman", "Newton", "Nguyen", "Nichols", "Nicholson", "Nielsen",
			"Nieves", "Nixon", "Noble", "Noel", "Nolan", "Norman", "Norris",
			"Norton", "Nunez", "Obrien", "Ochoa", "Oconnor", "Odom",
			"Odonnell", "Oliver", "Olsen", "Olson", "O'neal", "O'neil",
			"O'neill", "Orr", "Ortega", "Ortiz", "Osborn", "Osborne", "Owen",
			"Owens", "Pace", "Pacheco", "Padilla", "Page", "Palmer", "Park",
			"Parker", "Parks", "Parrish", "Parsons", "Pate", "Patel",
			"Patrick", "Patterson", "Patton", "Paul", "Payne", "Pearson",
			"Peck", "Pena", "Pennington", "Perez", "Perkins", "Perry",
			"Peters", "Petersen", "Peterson", "Petty", "Phelps", "Phillips",
			"Pickett", "Pierce", "Pittman", "Pitts", "Pollard", "Poole",
			"Pope", "Porter", "Potter", "Potts", "Powell", "Powers", "Pratt",
			"Preston", "Price", "Prince", "Pruitt", "Puckett", "Pugh", "Quinn",
			"Ramirez", "Ramos", "Ramsey", "Randall", "Randolph", "Rasmussen",
			"Ratliff", "Ray", "Raymond", "Reed", "Reese", "Reeves", "Reid",
			"Reilly", "Reyes", "Reynolds", "Rhodes", "Rice", "Rich", "Richard",
			"Richards", "Richardson", "Richmond", "Riddle", "Riggs", "Riley",
			"Rios", "Rivas", "Rivera", "Rivers", "Roach", "Robbins",
			"Roberson", "Roberts", "Robertson", "Robinson", "Robles", "Rocha",
			"Rodgers", "Rodriguez", "Rodriquez", "Rogers", "Rojas", "Rollins",
			"Roman", "Romero", "Rosa", "Rosales", "Rosario", "Rose", "Ross",
			"Roth", "Rowe", "Rowland", "Roy", "Ruiz", "Rush", "Russell",
			"Russo", "Rutledge", "Ryan", "Salas", "Salazar", "Salinas",
			"Sampson", "Sanchez", "Sanders", "Sandoval", "Sanford", "Santana",
			"Santiago", "Santos", "Sargent", "Saunders", "Savage", "Sawyer",
			"Schmidt", "Schneider", "Schroeder", "Schultz", "Schwartz",
			"Scott", "Sears", "Sellers", "Serrano", "Sexton", "Shaffer",
			"Shannon", "Sharp", "Sharpe", "Shaw", "Shelton", "Shepard",
			"Shepherd", "Sheppard", "Sherman", "Shields", "Short", "Silva",
			"Simmons", "Simon", "Simpson", "Sims", "Singleton", "Skinner",
			"Slater", "Sloan", "Small", "Snider", "Snow", "Snyder", "Solis",
			"Solomon", "Sosa", "Soto", "Sparks", "Spears", "Spence", "Spencer",
			"Stafford", "Stanley", "Stanton", "Stark", "Steele", "Stein",
			"Stephens", "Stephenson", "Stevens", "Stevenson", "Stewart",
			"Stokes", "Stone", "Stout", "Strickland", "Strong", "Stuart",
			"Suarez", "Sullivan", "Summers", "Sutton", "Swanson", "Sweeney",
			"Sweet", "Sykes", "Talley", "Tanner", "Tate", "Terrell", "Terry",
			"Thompson", "Thornton", "Tillman", "Todd", "Torres", "Townsend",
			"Tran", "Travis", "Trevino", "Trujillo", "Tucker", "Turner",
			"Tyler", "Tyson", "Underwood", "Valdez", "Valencia", "Valentine",
			"Valenzuela", "Vance", "Vang", "Vargas", "Vasquez", "Vaughan",
			"Vaughn", "Vazquez", "Vega", "Velasquez", "Velazquez", "Velez",
			"Van halen", "Vincent", "Vinson", "Wade", "Wagner", "Walker",
			"Wall", "Wallace", "Waller", "Walls", "Walsh", "Walter", "Walters",
			"Walton", "Ward", "Ware", "Warner", "Warren", "Washington",
			"Waters", "Watkins", "Watson", "Watts", "Weaver", "Webb", "Weber",
			"Webster", "Weeks", "Weiss", "Welch", "Wells", "West", "Wheeler",
			"Whitaker", "White", "Whitehead", "Whitfield", "Whitley",
			"Whitney", "Wiggins", "Wilcox", "Wilder", "Wiley", "Wilkerson",
			"Wilkins", "Wilkinson", "William", "Williamson", "Willis",
			"Winters", "Wise", "Witt", "Wolf", "Wolfe", "Wong", "Wood",
			"Woodard", "Woods", "Woodward", "Wooten", "Workman", "Wright",
			"Wyatt", "Wynn", "Yang", "Yates", "York", "Young", "Zamora",
			"Zimmerman"
	};
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.df.generate.IDataGenerator#generate()
	 */
	@Override
	public Object generate() {
		// TODO Auto-generated method stub
		final String WHITESPACE = " ";
		StringBuilder builder = new StringBuilder();
		//判断是否需要添加前缀
		if(getPrefix() != null && Boolean.parseBoolean(getPrefix())){
			builder.append(prefixes[(int)(Math.random()*prefixes.length)]).append(WHITESPACE);
		}
		//添加前名，再添加后名
		builder.append(firstNames[(int)(Math.random()*firstNames.length)]).append(WHITESPACE);
		builder.append(lastNames[(int)(Math.random() * lastNames.length)]);
		//判断是否需要后缀
		if(getSuffix() != null && Boolean.parseBoolean(getSuffix())){
			builder.append(WHITESPACE).append(suffixes[(int)(Math.random() * suffixes.length)]);
		}
		//判断是否需要做重复检验
		if(isUnique()){
			return checkUnique(builder.toString());
		}
		return builder.toString();
	}

}