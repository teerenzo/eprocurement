
insert into configuration_property (id, application,name, display_name, type, value, is_framework_configuration, version, entity_state) 
values
(gen_random_uuid (), 'Example', 'timeZone'        		   ,'Time Zone'                   	, 'STRING',  'Africa/Harare', true,  1,	'ACTIVE'),
(gen_random_uuid (), 'Example', 'defaultCountry'           ,'Default Country'               , 'STRING',  'RW'  		  	, true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'defaultLocale'            , 'Default language'             , 'STRING' , 'en_us'        , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'validateStrings'          , 'Validate Strings'             , 'BOOLEAN', 'true'         , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'trimStrings'              , 'Trimm Strings'                , 'BOOLEAN', 'true'         , true,  1,	'ACTIVE'),
(gen_random_uuid (), 'Example', 'collectHibernateStats'    , 'Collect Hibernate stats'      , 'BOOLEAN', 'false'        , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'hibernateStatsThreshold'  , 'Hibernate stats threshold'    , 'INT'    , '3000'         , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'collectTimerInfo'         , 'Collect timer info'        	, 'BOOLEAN', 'true'         , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'timerThreshold'           , 'Timer threshhold'             , 'INT'    , '200'          , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'printFullStacktrace'      , 'Print full stack'             , 'BOOLEAN', 'false'       	, true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'printNotHarmfulExceptions', 'Print non harmfull exceptions', 'BOOLEAN', 'false'       	, true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'displayLoggerLevel'      , 'Display Logger level'         	, 'BOOLEAN', 'false'        , true,  1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'config1'                 ,'config1'                       	, 'STRING',  'config'		, false, 1, 'ACTIVE'),
(gen_random_uuid (), 'Example', 'config2'                 , 'config2'                      	, 'BOOLEAN', 'true'  		, false, 1,	'ACTIVE'),
(gen_random_uuid (), 'Example', 'config3'                 , 'config3'                      	, 'STRING' , 'config'		, false, 1, 'ACTIVE');
 
 
insert into users (id, first_name, last_name, email, password, national_id,  version, entity_state)
values
(gen_random_uuid (), 'emmanuel', 'renzaho', 'kalisa@gmail.com', '12345', '1234786', 1, 'ACTIVE');


insert into roles (id, name, description, version, entity_state)
values
(gen_random_uuid (), 'ADMIN', 'Admin', 1, 'ACTIVE'),
(gen_random_uuid (), 'USER', 'User', 1, 'ACTIVE');


insert into users_roles (id, user_id, role_id, version, entity_state)
values
    (gen_random_uuid (), (select id from users where first_name = 'emmanuel'), (select id from roles where name = 'ADMIN'), 1, 'ACTIVE');
