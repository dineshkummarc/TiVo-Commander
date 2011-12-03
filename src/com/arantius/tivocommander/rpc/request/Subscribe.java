/*
Open Commander for TiVo allows control of a TiVo Premiere device.
Copyright (C) 2011  Anthony Lieuallen (arantius@gmail.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

package com.arantius.tivocommander.rpc.request;

import java.util.HashMap;

import org.codehaus.jackson.JsonNode;

import com.arantius.tivocommander.rpc.MindRpc;

public class Subscribe extends MindRpcRequest {
  public Subscribe() {
    super("subscribe");

    mDataMap.put("bodyId", MindRpc.mBodyId);
    mDataMap.put("recordingQuality", "best");
  }

  public void setCollection(String collectionId, JsonNode channel, int max,
      String which) {
    HashMap<String, Object> idSetSource = new HashMap<String, Object>();
    idSetSource.put("channel", channel);
    idSetSource.put("collectionId", collectionId);
    idSetSource.put("type", "seasonPassSource");
    mDataMap.put("idSetSource", idSetSource);
    mDataMap.put("maxRecordings", max);
    mDataMap.put("showStatus", which);
  }

  public void setIgnoreConflicts(boolean ignoreConflicts) {
    mDataMap.put("ignoreConflicts", ignoreConflicts);
  }

  public void setKeepUntil(String behavior) {
    mDataMap.put("keepBehavior", behavior);
  }

  public void setOffer(String offerId, String contentId) {
    HashMap<String, String> idSetSource = new HashMap<String, String>();
    idSetSource.put("contentId", contentId);
    idSetSource.put("offerId", offerId);
    idSetSource.put("type", "singleOfferSource");
    mDataMap.put("idSetSource", idSetSource);
  }

  public void setPadding(Integer start, Integer stop) {
    if (start != 0) {
      mDataMap.put("startTimePadding", start);
    }
    if (stop != 0) {
      mDataMap.put("endTimePadding", stop);
    }
  }

  public void setPriority(Integer priority) {
    if (priority > 0) {
      mDataMap.put("priority", 1);
    }
  }
}
