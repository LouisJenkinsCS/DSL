var nElements = 15;
var nLeftOver = nElements  - ((8 * (nElements / 8)));
var nIterations = nElements / 8;
var tape = "";
print "nIterations: " + nIterations + ", nLeftOver: " + nLeftOver;

while (nIterations) {
	tape = tape + "A";	
	tape = tape + "A";	
	tape = tape + "A";	
	tape = tape + "A";	
	tape = tape + "A";	
	tape = tape + "A";	
	tape = tape + "A";	
	tape = tape + "A";	

	nIterations = nIterations - 1;
}

if (nLeftOver >= 7) { tape = tape + "A";	}
if (nLeftOver >= 6) { tape = tape + "A";	}
if (nLeftOver >= 5) { tape = tape + "A";	}
if (nLeftOver >= 4) { tape = tape + "A";	}
if (nLeftOver >= 3) { tape = tape + "A";	}
if (nLeftOver >= 2) { tape = tape + "A";	}
if (nLeftOver >= 1) { tape = tape + "A";	}

print tape;
