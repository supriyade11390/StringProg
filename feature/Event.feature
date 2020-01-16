Feature: Event

Background:  Login to the Application 
  Given User login with his credentials 
  Then User Select Space "Space_OneHub_SAM_UAT" in My Spaces at Home Page
  Then User click to Dashboard and Dashboard page is Display
  
#@EventCollection    
#Scenario Outline: Verify the Execution Dashboard of Event collection
#   When User Select Event and Execution dashboard
# Then User select "<StartDate>" and "<EndDate>" using date range filter
#   Then Verify the KVI and tooltips available on Dashbaord 
#   |Avg Event Count|txt_dashlet_AvgEventCount|tooltip_Avg_Event_Count|
#   |Competed %|txt_dashlet_CompletedPercentage|tooltip_Completed_%|
#   |Completed In Window % |txt_dashlet_CompletedInWindowPercentage|tooltip_Completed_in_Window_%|
#   |Scheduled % |txt_dashlet_Schedulepercentage|tooltip_schedule_%|
#   |Exceptions %|txt_dashlet_ExptionPercentage|tooltip_Exceptions_%| 
#   |Rolled Events|txt_dashlet_RolledEvents|tooltip_Rolled_Event|  
#   |Projects |txt_dashlet_Projects|tooltip_Projects|   
#   |Events|txt_dashlet_Events|tooltip_Events|
#   |Pending Events |txt_dashlet_PendingEvents|tooltip_Pending_Events|
#  Then Verify the Filters available on dashboard
#    |Event |lnk_Filter_Event|
#    |Account (Supplier)|lnk_Filter_Account(Supplier)|
#    |Buyer|lnk_Filter_Buyer|
###  |Category|lnk_Filter_Category|
#    |Club Region|lnk_Filter_ClubRegion|
#    |Club Market|lnk_Filter_ClubMarket|
###  |Club|lnk_Filter_Club|
###  |General Market Manager|lnk_Filter_GeneralMarketMgr|
#    |Divisional Market Manager|lnk_Filter_DivisionalMarketMgr|
#    |Event Type|lnk_Filter_EventType|
#    |Event Day|lnk_Filter_EventDay|
#    |Opportunity|lnk_Filter_Opprtunity|
#    |Service|lnk_Filter_Service|
# Then Verify Columns that are displayed of Event Summary Dashlet
#     |Event|col_txt_Event|
#     |EMS ID|col_txt_EMSID|
#     |Start Date|col_txt_StartDate|
#     |End Date|col_txt_EndDate|
#     |Club Funded?|col_txt_ClubFunded|
#     |Event Type|col_txt_EventType|
#     |Total Events|col_txt_TotalEvents|
#     |Completed Events|col_txt_CompletedEvents|
#     |Completion %|col_txt_Completion%|
#     |Scheduled %|//span[text()='Scheduled %']|
#     |Completed In Window|col_txt_CompletedInWindow|
#     |Exception Events|col_txt_ExceptionsEvent|
#     |Not Completed Events|col_txt_NotCompletedEvent|
# Then Verify the navigations on Execution Dashboard from KVI
#     |Completed In window %|txt_dashlet_CompletedInWindowPercentage|
#     |Exception % |txt_dashlet_ExptionPercentage|
#     |Rolled Event|txt_dashlet_RolledEvents|
#     |Events|txt_dashlet_Events|    
#     |Pending Events|txt_dashlet_PendingEvents|   
#     |Event Name|col_event_name_txt|
#  Then Verify that Total Event Count of Event summary matches with the Events KVI
#  And Verify that Completion % count  of Event summary matches with the Completed % KVI
#  Then  Verify that Scheduled % count of Event Summary matches with thw scheduled % KVI 
#  Then Verify the data on  Event Type Performance Dashlet with other dashlet
  
#Examples:
# |StartDate||EndDate|
# |06/14/2019||08/02/2019|

#Scenario Outline: Verify the Event By Category Dashboard of Event Sales collection
#   When User Select Event By Category and Event Sales 
#   Then User select "<StartDate>" and "<EndDate>" using Event Date filter
#   Then Verify the KVI available on the Dashboard
#    |Event Day Sales|txt_KVI_EventDaySales|
#    |Event Day Lift (By Dollars)|txt_KVI_Event_Day_Lift_by_dollar|
#    |Event Day Lift (By Units)|txt_KVI_Event_day_lift_by_unit|
#   And Verify the tooltips available on Dashbaord
#    |Event Day Sales|tooltip_EventDaySales|
#    |Event Day Lift (By Dollars)|tooltip_Event_Day_Lift_by_dollar|
#    |Event Day Lift (By Units)|tooltip_Event_Day_Lift_By_Units|
  #Then Verify Filters are available on dashboard
 #  |No of Weeks to Consider for Lift|lnk_Filter_No_of_Weeks_consider_for_Lift|
#    |Event Name and EMS ID|lnk_Filter_Event_name_EMS_Id|
#    |Club|lnk_Filter_Club|
#    |Club Region|lnk_Filter_Club_region|
#    |Club Market|lnk_Filter_Club_Market|
#    |Account Name (Vendor)|lnk_Filter_AccountName|
#    |Category Name|link_Filter_Category_Name|
#   Then Verify the navigations from category Link
#   Then Verify Columns that are displayed of Sales By Category Dashlet
#   Then Verify the list of column names in dashlet "Category Dashlet"   
#    |Category|col_txt_category|
#    |Category Name|col_txt_categoryname|
#    |Event Day Sales|col_txt_Event_day_sales|
#    |Event Day Units Moved|col_txt_Event_dayunits_moved|
#    |Prior Week Day Avg. Sales|col_txt_prior_week_avg_sale|
#    |Prior Week Day Avg Units Moved|col_txt_prior_week_avg_unit|
#    |Event Day Lift (By Dollars)|col_txt_Event_Day_Lift_(By_Dollars)|
#    |Event Day Lift (By Units)|col_txt_Event_Day_Lift_(By_Units)|
#    |Event Week Sales|col_txt_Event_Week_Sales|
#    |Event Week Units Moved|col_txt_Event_Week_Units_Moved|
#    |Prior Week Avg. Sales|col_txt_Prior_Week_Avg_Sales|
#    |Prior Week Avg. Units Moved|col_txt_Prior_Week_Avg_Unit|
#    |Event Week Lift (By Dollars)|col_txt_Event_Day_Week_(By_Dollars)|
#    |Event Week Lift (By Units)|col_txt_Event_Day_Week_(By_Units)|
#    |Post Week Avg. Sales|col_txt_Post_Week_Avg_Sales|
#    |Post Week Avg. Units Moved|col_txt_Post_Week_Avg_Unit|
#    |Sustained Lift (By Dollars)|col_txt_Sustained_Lift_(By_Dollars)|
#    |Sustained Lift (By Units)|col_txt_Sustained_Lift_(By_Units)|
#Examples:
# |StartDate||EndDate|
# |06/14/2019||08/02/2019|
    
 Scenario Outline:Verify the Club Analysis dashboard of Event Collection
 When User select "Club_Analysis" and "Events" Collection
 Then User select "<StartDate>" and "<EndDate>" using Event Date filter
 Then Verify the KVI available on the Dashboard
  |Events|txt_club_kvi_events|
  |scheduled %|txt_club_kvi_Sche%|
  |Completed Events|txt_club_kvi_CompletedEvent|
  |Event Exceptions|txt_club_kvi_Event_Exceptions|
  |Rolled Events|txt_club_kvi_Rolled_Events|
  |Projects|txt_club_kvi_projects|
  |avg Event Count|txt_club_kvi_Avg_event_count|
Then Verify the tooltips available on Dashbaord   
  |Events|tooltip_club_Events|
  |Scheduled %|tooltip_club_Sch%|
  |Completed Events|tooltip_club_CompletedEvent|
  |Event Exceptions|tooltip_Club_Event_Exceptions|
  |Rolled Events|tooltip_Club_Rolled_Events|
  |Projects|tooltip_club_projects|
  |Avg Event Count|tooltip_club_Avg_event_count|
Then Verify Filters are available on dashboard
 |Account (Supplier)|lnk_Filter_Account(Supplier)|
   |Buyer|lnk_Filter_Buyer|
#   |Category|lnk_Filter_Category|
#   |Club|lnk_Filter_Club|
   |Club Market|lnk_Filter_Club_Market|
   |Club Region|lnk_Filter_ClubRegion|
   |Divisional Market Manager|lnk_Filter_DivisionalMarketMgr|
   |Event |lnk_Filter_Event|
   |Event Day|lnk_Filter_EventDay|
   |Event Type|lnk_Filter_EventType|
#   |General Market Manager|lnk_Filter_GeneralMarketMgr|
   |Service|lnk_Filter_Service|
 Then Verify the list of column names in dashlet "Club Summary"   
   |Region|col_txt_Region|
   |Market|col_txt_Market|
   |Club|col_txt_Club|
   |City|col_txt_city|
   |State|col_txt_State|
   |ZipCode|col_txt_ZipCode|
   |Events|col_txt_Events|
   |Exception Events|col_txt_ExceptionEvents|
   |Rolled Events|col_txt_Rolled_Event|
   |Scheduled %|col_txt_Scheduled%|
   |Completion %|col_txt_Completion%|
   |Exception %|col_txt_Exception%|
  When User click on Event Exception KVI navigate to the Event Exception Analysis
  When User click on ClubNo navigate to the Club Events Report
  
 Examples: 
  |StartDate||EndDate|
 |06/14/2019||08/02/2019|