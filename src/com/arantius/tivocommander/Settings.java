package com.arantius.tivocommander;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("TiVo Commander - Settings");
    addPreferencesFromResource(R.xml.preferences);
  }
}
