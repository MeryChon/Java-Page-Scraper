CREATE DATABASE PAGE_SCRAPER_DB;
USE PAGE_SCRAPER_DB;

drop table if exists visited_urls;
CREATE TABLE IF NOT EXISTS visited_urls (
	id int primary key auto_increment,
    url varchar(200) NOT NULL,
    date_visited datetime
    
);


drop table if exists scraped_links;
CREATE TABLE IF NOT EXISTS scraped_links(
	id int primary key auto_increment,
    url_id int not null, 
    link_url varchar(400)
);


drop table if exists scraped_image_sources;
CREATE TABLE IF NOT EXISTS scraped_image_sources (
	id int primary key auto_increment,
    url_id int not null, 
    image_src varchar(400)
);
