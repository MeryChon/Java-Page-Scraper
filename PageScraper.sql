CREATE DATABASE PAGE_SCRAPER_DB;

CREATE TABLE IF NOT EXISTS visited_links (
	id int primary key,
    url varchar(128) NOT NULL,
    date_visited datetime
    
);


CREATE TABLE IF NOT EXISTS scraped_links(
	id int primary key,
    url_id int not null, 
    link_url varchar(128)
);


CREATE TABLE IF NOT EXISTS scraped_image_sources (
	id int primary key,
    url_id int not null, 
    image_src varchar(128)
);

drop table visited_links;