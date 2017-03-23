package com.rocketleague.refdata;

public enum Arena {
  DFH_STADIUM("DFH Stadium"),
  URBAN_CENTRAL("Urban Central"),
  MANNFIELD("Mannfield"),
  BECKWITH_PARK("Beckwith Park"),
  UTOPIA_COLISEUM("Utopia Coliseum"),
  WASTELAND("Wasteland"),
  DUNK_HOUSE("Dunk House"),
  NEO_TOKYO("Neo Tokyo"),
  AQUADOME("Aquadome"),
  STARBASE_ARC("Starbase ARC");

  private final String nameForUi;

  Arena(String nameForUi) {
    this.nameForUi = nameForUi;
  }

  public String getNameForUi() {
    return nameForUi;
  }
}
