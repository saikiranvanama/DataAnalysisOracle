CREATE TABLE YELPUSER
(   yelping_since date,
    funny integer,
    useful integer,
    cool integer,
    review_count integer,
    userName varchar2(50),
    user_id varchar2(50),
    fans number,
    average_stars number,
    userType varchar2(50),
    compliments_profile integer,
    compliments_funny integer,
    compliments_cute integer,
    compliments_plain integer,
    compliments_writer integer,
    compliments_note integer,
    compliments_photos integer,
    compliments_hot integer,
    compliments_cool integer,
    compliments_more integer,
    PRIMARY KEY (user_id)
);

Create table UserFriends
(   user_id varchar2(50),
    friendId varchar2(50),
    foreign key (user_id) REFERENCES YELPUSER(user_id) INITIALLY DEFERRED DEFERRABLE,
    foreign key (friendId) REFERENCES YELPUSER(user_id) INITIALLY DEFERRED DEFERRABLE
);

create table userElite
(    user_id varchar2(50),
     elite integer,
     foreign key (user_id) REFERENCES YELPUSER(user_id)INITIALLY DEFERRED DEFERRABLE
);

create table yelpbusiness(
  business_id varchar2(50),
  full_address varchar2(100),
  openStatus varchar(10) check (openStatus in ('true', 'false')),
  city varchar2(50),
  review_count integer,
  businessName varchar2(50),
  longitude float,
  state varchar2(50),
  stars float,
  latitude float,
  bType varchar(50),
  primary key(business_id)
);

create table businesstimings(
  business_id varchar2(50),
  MondayOpen timestamp,
  MondayClose timestamp,
  TuesdayOpen timestamp,
  TuesdayClose timestamp,
  WednesdayOpen timestamp,
  WednesdayClose timestamp,
  ThursdayOpen timestamp,
  ThursdayClose timestamp,
  FridayOpen timestamp,
  FridayClose timestamp,
  SaturdayOpen timestamp,
  SaturdayClose timestamp,
  SundayOpen timestamp,
  SundayClose timestamp,
  foreign key (business_id) references yelpBusiness(business_id)
);

create table businessCategories(
  business_id varchar2(50),
  categoryName varchar2(50),
  foreign key (business_id) references yelpBusiness(business_id)
);

create table businessAttributes(
  business_id varchar2(50),
  attributeKeys varchar2(100),
  attributeValue varchar2(2000),
  foreign key (business_id) references yelpBusiness(business_id)
);

select count(distinct business_id) from businessattributes;

