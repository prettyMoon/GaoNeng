// Generated code from Butter Knife. Do not modify!
package com.szdd.qianxun.message.friend;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FriendListAdapter$ViewHolder$$ViewBinder<T extends com.szdd.qianxun.message.friend.FriendListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131428074, "field 'msgFriendListItemIcon'");
    target.msgFriendListItemIcon = finder.castView(view, 2131428074, "field 'msgFriendListItemIcon'");
    view = finder.findRequiredView(source, 2131428075, "field 'msgFriendListItemName'");
    target.msgFriendListItemName = finder.castView(view, 2131428075, "field 'msgFriendListItemName'");
    view = finder.findRequiredView(source, 2131428076, "field 'msgFriendListItemId'");
    target.msgFriendListItemId = finder.castView(view, 2131428076, "field 'msgFriendListItemId'");
    view = finder.findRequiredView(source, 2131428077, "field 'msgFriendListItemState'");
    target.msgFriendListItemState = finder.castView(view, 2131428077, "field 'msgFriendListItemState'");
  }

  @Override public void unbind(T target) {
    target.msgFriendListItemIcon = null;
    target.msgFriendListItemName = null;
    target.msgFriendListItemId = null;
    target.msgFriendListItemState = null;
  }
}
