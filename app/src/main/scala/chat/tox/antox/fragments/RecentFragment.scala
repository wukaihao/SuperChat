package chat.tox.antox.fragments

import java.sql.Timestamp

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.{TextView, ListView}
import chat.tox.antox.R
import chat.tox.antox.adapters.ContactListAdapter
import chat.tox.antox.utils.{LeftPaneItem, TimestampUtils}
import chat.tox.antox.wrapper._

 abstract class RecentFragment extends AbstractMessageFragment(showSearch = true, showFab = true) {

   var lv_message_top : ListView = _
   var tv_constant : TextView = _
   var room_recycleView : RecyclerView = _
   var tv_morechat : TextView = _
   var lv_message_boom: ListView = _
   val topListViewAdapter : TopListViewAdapter = _


   override def updateContacts(contactInfoTuple: (Seq[FriendInfo], Seq[FriendRequest],
    Seq[GroupInvite], Seq[GroupInfo])) {
    contactInfoTuple match {
      case (friendsList, friendRequests, groupInvites, groupList) =>
        leftPaneAdapter = new ContactListAdapter(getActivity)
        updateContactsLists(leftPaneAdapter, friendsList ++ groupList)
        contactsListView.setAdapter(leftPaneAdapter)
    }
  }

  def initView() = {
    topListViewAdapter = new TopListViewAdapter(getContext)
  }

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    val rootView = super.onCreateView(inflater, container, savedInstanceState)
    rootView.findViewById(R.id.center_text).setVisibility(View.VISIBLE)
    initView()
    rootView
  }

  def updateContactsLists(leftPaneAdapter: ContactListAdapter, contactList: Seq[ContactInfo]): Unit = {
    val sortedContactList = contactList.filter(c => c.lastMessage.isDefined).sortWith(compareNames).sortWith(compareLastMessageTimestamp)
    if (sortedContactList.nonEmpty) {
      getView.findViewById(R.id.center_text).setVisibility(View.GONE)
      for (contact <- sortedContactList) {
        val itemType = if (contact.isInstanceOf[GroupInfo]) {
          ContactItemType.GROUP
        } else {
          ContactItemType.FRIEND
        }

        val lastMessage = contact.lastMessage.get
        val contactPaneItem = new LeftPaneItem(itemType, contact.key, contact.avatar, contact.getDisplayName, lastMessage.toNotificationFormat(getActivity),
          contact.online, UserStatus.getToxUserStatusFromString(contact.status), contact.favorite, contact.unreadCount,
          lastMessage.timestamp)
        leftPaneAdapter.addItem(contactPaneItem)
      }
    } else {
      getView.findViewById(R.id.center_text).setVisibility(View.VISIBLE)
    }
  }

  def compareLastMessageTimestamp(a: ContactInfo, b: ContactInfo): Boolean = {
    def lastMessageTimstamp(info: ContactInfo): Timestamp =
      info.lastMessage.map(_.timestamp).getOrElse(TimestampUtils.emptyTimestamp())

    lastMessageTimstamp(a).after(lastMessageTimstamp(b))
  }
}
