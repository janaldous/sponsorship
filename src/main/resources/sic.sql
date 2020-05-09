--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: sic; Type: TABLE; Schema: public; Owner: tarsier
--

CREATE TABLE public.sic (
    code character varying(255) NOT NULL,
    description character varying(255),
    interested boolean DEFAULT false
);


ALTER TABLE public.sic OWNER TO tarsier;

--
-- Data for Name: sic; Type: TABLE DATA; Schema: public; Owner: tarsier
--

COPY public.sic (code, description, interested) FROM stdin;
01110	Growing of cereals (except rice), leguminous crops and oil seeds	f
01120	Growing of rice	f
01130	Growing of vegetables and melons, roots and tubers	f
01140	Growing of sugar cane	f
01150	Growing of tobacco	f
01160	Growing of fibre crops	f
01190	Growing of other non-perennial crops	f
01210	Growing of grapes	f
01220	Growing of tropical and subtropical fruits	f
01230	Growing of citrus fruits	f
01240	Growing of pome fruits and stone fruits	f
01250	Growing of other tree and bush fruits and nuts	f
01260	Growing of oleaginous fruits	f
01270	Growing of beverage crops	f
01280	Growing of spices, aromatic, drug and pharmaceutical crops	f
01290	Growing of other perennial crops	f
01300	Plant propagation	f
01410	Raising of dairy cattle	f
01420	Raising of other cattle and buffaloes	f
01430	Raising of horses and other equines	f
01440	Raising of camels and camelids	f
01450	Raising of sheep and goats	f
01460	Raising of swine/pigs	f
01470	Raising of poultry	f
01490	Raising of other animals	f
01500	Mixed farming	f
01610	Support activities for crop production	f
01621	Farm animal boarding and care	f
01629	Support activities for animal production (other than farm animal boarding and care) n.e.c.	f
01630	Post-harvest crop activities	f
01640	Seed processing for propagation	f
01700	Hunting, trapping and related service activities	f
02100	Silviculture and other forestry activities	f
02200	Logging	f
02300	Gathering of wild growing non-wood products	f
02400	Support services to forestry	f
03110	Marine fishing	f
03120	Freshwater fishing	f
03210	Marine aquaculture	f
03220	Freshwater aquaculture	f
05101	Deep coal mines	f
05102	Open cast coal working	f
05200	Mining of lignite	f
06100	Extraction of crude petroleum	f
06200	Extraction of natural gas	f
07100	Mining of iron ores	f
07210	Mining of uranium and thorium ores	f
07290	Mining of other non-ferrous metal ores	f
08110	Quarrying of ornamental and building stone, limestone, gypsum, chalk and slate	f
08120	Operation of gravel and sand pits; mining of clays and kaolin	f
08910	Mining of chemical and fertilizer minerals	f
08920	Extraction of peat	f
08930	Extraction of salt	f
08990	Other mining and quarrying n.e.c.	f
09100	Support activities for petroleum and natural gas mining	f
09900	Support activities for other mining and quarrying	f
10110	Processing and preserving of meat	f
10120	Processing and preserving of poultry meat	f
10130	Production of meat and poultry meat products	f
10200	Processing and preserving of fish, crustaceans and molluscs	f
10310	Processing and preserving of potatoes	f
10320	Manufacture of fruit and vegetable juice	f
10390	Other processing and preserving of fruit and vegetables	f
10410	Manufacture of oils and fats	f
10420	Manufacture of margarine and similar edible fats	f
10511	Liquid milk and cream production	f
10512	Butter and cheese production	f
10519	Manufacture of other milk products	f
10520	Manufacture of ice cream	f
10611	Grain milling	f
10612	Manufacture of breakfast cereals and cereals-based food	f
10620	Manufacture of starches and starch products	f
10710	Manufacture of bread; manufacture of fresh pastry goods and cakes	f
10720	Manufacture of rusks and biscuits; manufacture of preserved pastry goods and cakes	f
10730	Manufacture of macaroni, noodles, couscous and similar farinaceous products	f
10810	Manufacture of sugar	f
10821	Manufacture of cocoa and chocolate confectionery	f
10822	Manufacture of sugar confectionery	f
10831	Tea processing	f
10832	Production of coffee and coffee substitutes	f
10840	Manufacture of condiments and seasonings	f
10850	Manufacture of prepared meals and dishes	f
10860	Manufacture of homogenized food preparations and dietetic food	f
10890	Manufacture of other food products n.e.c.	f
10910	Manufacture of prepared feeds for farm animals	f
10920	Manufacture of prepared pet foods	f
11010	Distilling, rectifying and blending of spirits	f
11020	Manufacture of wine from grape	f
11030	Manufacture of cider and other fruit wines	f
11040	Manufacture of other non-distilled fermented beverages	f
11050	Manufacture of beer	f
11060	Manufacture of malt	f
11070	Manufacture of soft drinks; production of mineral waters and other bottled waters	f
12000	Manufacture of tobacco products	f
13100	Preparation and spinning of textile fibres	f
13200	Weaving of textiles	f
13300	Finishing of textiles	f
13910	Manufacture of knitted and crocheted fabrics	f
13921	Manufacture of soft furnishings	f
13922	manufacture of canvas goods, sacks, etc.	f
13923	manufacture of household textiles	f
13931	Manufacture of woven or tufted carpets and rugs	f
13939	Manufacture of other carpets and rugs	f
13940	Manufacture of cordage, rope, twine and netting	f
13950	Manufacture of non-wovens and articles made from non-wovens, except apparel	f
13960	Manufacture of other technical and industrial textiles	f
13990	Manufacture of other textiles n.e.c.	f
14110	Manufacture of leather clothes	f
14120	Manufacture of workwear	f
14131	Manufacture of other men's outerwear	f
14132	Manufacture of other women's outerwear	f
14141	Manufacture of men's underwear	f
14142	Manufacture of women's underwear	f
14190	Manufacture of other wearing apparel and accessories n.e.c.	f
14200	Manufacture of articles of fur	f
14310	Manufacture of knitted and crocheted hosiery	f
14390	Manufacture of other knitted and crocheted apparel	f
15110	Tanning and dressing of leather; dressing and dyeing of fur	f
15120	Manufacture of luggage, handbags and the like, saddlery and harness	f
15200	Manufacture of footwear	f
16100	Sawmilling and planing of wood	f
16210	Manufacture of veneer sheets and wood-based panels	f
16220	Manufacture of assembled parquet floors	f
16230	Manufacture of other builders' carpentry and joinery	f
16240	Manufacture of wooden containers	f
16290	Manufacture of other products of wood; manufacture of articles of cork, straw and plaiting materials	f
17110	Manufacture of pulp	f
17120	Manufacture of paper and paperboard	f
17211	Manufacture of corrugated paper and paperboard, sacks and bags	f
17219	Manufacture of other paper and paperboard containers	f
17220	Manufacture of household and sanitary goods and of toilet requisites	f
17230	Manufacture of paper stationery	f
17240	Manufacture of wallpaper	f
17290	Manufacture of other articles of paper and paperboard n.e.c.	f
18110	Printing of newspapers	f
18121	Manufacture of printed labels	f
18129	Printing n.e.c.	f
18130	Pre-press and pre-media services	f
18140	Binding and related services	f
18201	Reproduction of sound recording	f
18202	Reproduction of video recording	f
18203	Reproduction of computer media	f
19100	Manufacture of coke oven products	f
19201	Mineral oil refining	f
19209	Other treatment of petroleum products (excluding petrochemicals manufacture)	f
20110	Manufacture of industrial gases	f
20120	Manufacture of dyes and pigments	f
20130	Manufacture of other inorganic basic chemicals	f
20140	Manufacture of other organic basic chemicals	f
20150	Manufacture of fertilizers and nitrogen compounds	f
20160	Manufacture of plastics in primary forms	f
20170	Manufacture of synthetic rubber in primary forms	f
20200	Manufacture of pesticides and other agrochemical products	f
20301	Manufacture of paints, varnishes and similar coatings, mastics and sealants	f
20302	Manufacture of printing ink	f
20411	Manufacture of soap and detergents	f
20412	Manufacture of cleaning and polishing preparations	f
20420	Manufacture of perfumes and toilet preparations	f
20510	Manufacture of explosives	f
20520	Manufacture of glues	f
20530	Manufacture of essential oils	f
20590	Manufacture of other chemical products n.e.c.	f
20600	Manufacture of man-made fibres	f
21100	Manufacture of basic pharmaceutical products	f
21200	Manufacture of pharmaceutical preparations	f
22110	Manufacture of rubber tyres and tubes; retreading and rebuilding of rubber tyres	f
22190	Manufacture of other rubber products	f
22210	Manufacture of plastic plates, sheets, tubes and profiles	f
22220	Manufacture of plastic packing goods	f
22230	Manufacture of builders ware of plastic	f
22290	Manufacture of other plastic products	f
23110	Manufacture of flat glass	f
23120	Shaping and processing of flat glass	f
23130	Manufacture of hollow glass	f
23140	Manufacture of glass fibres	f
23190	Manufacture and processing of other glass, including technical glassware	f
23200	Manufacture of refractory products	f
23310	Manufacture of ceramic tiles and flags	f
23320	Manufacture of bricks, tiles and construction products, in baked clay	f
23410	Manufacture of ceramic household and ornamental articles	f
23420	Manufacture of ceramic sanitary fixtures	f
23430	Manufacture of ceramic insulators and insulating fittings	f
23440	Manufacture of other technical ceramic products	f
23490	Manufacture of other ceramic products n.e.c.	f
23510	Manufacture of cement	f
23520	Manufacture of lime and plaster	f
23610	Manufacture of concrete products for construction purposes	f
23620	Manufacture of plaster products for construction purposes	f
23630	Manufacture of ready-mixed concrete	f
23640	Manufacture of mortars	f
23650	Manufacture of fibre cement	f
23690	Manufacture of other articles of concrete, plaster and cement	f
23700	Cutting, shaping and finishing of stone	f
23910	Production of abrasive products	f
23990	Manufacture of other non-metallic mineral products n.e.c.	f
24100	Manufacture of basic iron and steel and of ferro-alloys	f
24200	Manufacture of tubes, pipes, hollow profiles and related fittings, of steel	f
24310	Cold drawing of bars	f
24320	Cold rolling of narrow strip	f
24330	Cold forming or folding	f
24340	Cold drawing of wire	f
24410	Precious metals production	f
24420	Aluminium production	f
24430	Lead, zinc and tin production	f
24440	Copper production	f
24450	Other non-ferrous metal production	f
24460	Processing of nuclear fuel	f
24510	Casting of iron	f
24520	Casting of steel	f
24530	Casting of light metals	f
24540	Casting of other non-ferrous metals	f
25110	Manufacture of metal structures and parts of structures	f
25120	Manufacture of doors and windows of metal	f
25210	Manufacture of central heating radiators and boilers	f
25290	Manufacture of other tanks, reservoirs and containers of metal	f
25300	Manufacture of steam generators, except central heating hot water boilers	f
25400	Manufacture of weapons and ammunition	f
25500	Forging, pressing, stamping and roll-forming of metal; powder metallurgy	f
25610	Treatment and coating of metals	f
25620	Machining	f
25710	Manufacture of cutlery	f
25720	Manufacture of locks and hinges	f
25730	Manufacture of tools	f
25910	Manufacture of steel drums and similar containers	f
25920	Manufacture of light metal packaging	f
25930	Manufacture of wire products, chain and springs	f
25940	Manufacture of fasteners and screw machine products	f
25990	Manufacture of other fabricated metal products n.e.c.	f
26110	Manufacture of electronic components	f
26120	Manufacture of loaded electronic boards	f
26200	Manufacture of computers and peripheral equipment	f
26301	Manufacture of telegraph and telephone apparatus and equipment	f
26309	Manufacture of communication equipment other than telegraph, and telephone apparatus and equipment	f
26400	Manufacture of consumer electronics	f
26511	Manufacture of electronic measuring, testing etc. equipment, not for industrial process control	f
26512	Manufacture of electronic industrial process control equipment	f
26513	Manufacture of non-electronic measuring, testing etc. equipment, not for industrial process control	f
26514	Manufacture of non-electronic industrial process control equipment	f
26520	Manufacture of watches and clocks	f
26600	Manufacture of irradiation, electromedical and electrotherapeutic equipment	f
26701	Manufacture of optical precision instruments	f
26702	Manufacture of photographic and cinematographic equipment	f
26800	Manufacture of magnetic and optical media	f
27110	Manufacture of electric motors, generators and transformers	f
27120	Manufacture of electricity distribution and control apparatus	f
27200	Manufacture of batteries and accumulators	f
27310	Manufacture of fibre optic cables	f
27320	Manufacture of other electronic and electric wires and cables	f
27330	Manufacture of wiring devices	f
27400	Manufacture of electric lighting equipment	f
27510	Manufacture of electric domestic appliances	f
27520	Manufacture of non-electric domestic appliances	f
27900	Manufacture of other electrical equipment	f
28110	Manufacture of engines and turbines, except aircraft, vehicle and cycle engines	f
28120	Manufacture of fluid power equipment	f
28131	Manufacture of pumps	f
28132	Manufacture of compressors	f
28140	Manufacture of taps and valves	f
28150	Manufacture of bearings, gears, gearing and driving elements	f
28210	Manufacture of ovens, furnaces and furnace burners	f
28220	Manufacture of lifting and handling equipment	f
28230	Manufacture of office machinery and equipment (except computers and peripheral equipment)	f
28240	Manufacture of power-driven hand tools	f
28250	Manufacture of non-domestic cooling and ventilation equipment	f
28290	Manufacture of other general-purpose machinery n.e.c.	f
28301	Manufacture of agricultural tractors	f
28302	Manufacture of agricultural and forestry machinery other than tractors	f
28410	Manufacture of metal forming machinery	f
28490	Manufacture of other machine tools	f
28910	Manufacture of machinery for metallurgy	f
28921	Manufacture of machinery for mining	f
28922	Manufacture of earthmoving equipment	f
28923	Manufacture of equipment for concrete crushing and screening and roadworks	f
28930	Manufacture of machinery for food, beverage and tobacco processing	f
28940	Manufacture of machinery for textile, apparel and leather production	f
28950	Manufacture of machinery for paper and paperboard production	f
28960	Manufacture of plastics and rubber machinery	f
28990	Manufacture of other special-purpose machinery n.e.c.	f
29100	Manufacture of motor vehicles	f
29201	Manufacture of bodies (coachwork) for motor vehicles (except caravans)	f
29202	Manufacture of trailers and semi-trailers	f
29203	Manufacture of caravans	f
29310	Manufacture of electrical and electronic equipment for motor vehicles and their engines	f
29320	Manufacture of other parts and accessories for motor vehicles	f
30110	Building of ships and floating structures	f
30120	Building of pleasure and sporting boats	f
30200	Manufacture of railway locomotives and rolling stock	f
30300	Manufacture of air and spacecraft and related machinery	f
30400	Manufacture of military fighting vehicles	f
30910	Manufacture of motorcycles	f
30920	Manufacture of bicycles and invalid carriages	f
30990	Manufacture of other transport equipment n.e.c.	f
31010	Manufacture of office and shop furniture	f
31020	Manufacture of kitchen furniture	f
31030	Manufacture of mattresses	f
31090	Manufacture of other furniture	f
32110	Striking of coins	f
32120	Manufacture of jewellery and related articles	f
32130	Manufacture of imitation jewellery and related articles	f
32200	Manufacture of musical instruments	f
32300	Manufacture of sports goods	f
32401	Manufacture of professional and arcade games and toys	f
32409	Manufacture of other games and toys, n.e.c.	f
32500	Manufacture of medical and dental instruments and supplies	f
32910	Manufacture of brooms and brushes	f
32990	Other manufacturing n.e.c.	f
33110	Repair of fabricated metal products	f
33120	Repair of machinery	f
33130	Repair of electronic and optical equipment	f
33140	Repair of electrical equipment	f
33150	Repair and maintenance of ships and boats	f
33160	Repair and maintenance of aircraft and spacecraft	f
33170	Repair and maintenance of other transport equipment n.e.c.	f
33190	Repair of other equipment	f
33200	Installation of industrial machinery and equipment	f
35110	Production of electricity	f
35120	Transmission of electricity	f
35130	Distribution of electricity	f
35140	Trade of electricity	f
35210	Manufacture of gas	f
35220	Distribution of gaseous fuels through mains	f
35230	Trade of gas through mains	f
35300	Steam and air conditioning supply	f
36000	Water collection, treatment and supply	f
37000	Sewerage	f
38110	Collection of non-hazardous waste	f
38120	Collection of hazardous waste	f
38210	Treatment and disposal of non-hazardous waste	f
38220	Treatment and disposal of hazardous waste	f
38310	Dismantling of wrecks	f
38320	Recovery of sorted materials	f
39000	Remediation activities and other waste management services	f
41100	Development of building projects	f
41201	Construction of commercial buildings	f
41202	Construction of domestic buildings	f
42110	Construction of roads and motorways	f
42120	Construction of railways and underground railways	f
42130	Construction of bridges and tunnels	f
42210	Construction of utility projects for fluids	f
42220	Construction of utility projects for electricity and telecommunications	f
42910	Construction of water projects	f
42990	Construction of other civil engineering projects n.e.c.	f
43110	Demolition	f
43120	Site preparation	f
43130	Test drilling and boring	f
43210	Electrical installation	f
43220	Plumbing, heat and air-conditioning installation	f
43290	Other construction installation	f
43310	Plastering	f
43320	Joinery installation	f
43330	Floor and wall covering	f
43341	Painting	f
43342	Glazing	f
43390	Other building completion and finishing	f
43910	Roofing activities	f
43991	Scaffold erection	f
43999	Other specialised construction activities n.e.c.	f
45111	Sale of new cars and light motor vehicles	f
45112	Sale of used cars and light motor vehicles	f
45190	Sale of other motor vehicles	f
45200	Maintenance and repair of motor vehicles	f
45310	Wholesale trade of motor vehicle parts and accessories	f
45320	Retail trade of motor vehicle parts and accessories	f
45400	Sale, maintenance and repair of motorcycles and related parts and accessories	f
46110	Agents selling agricultural raw materials, livestock, textile raw materials and semi-finished goods	f
46120	Agents involved in the sale of fuels, ores, metals and industrial chemicals	f
46130	Agents involved in the sale of timber and building materials	f
46140	Agents involved in the sale of machinery, industrial equipment, ships and aircraft	f
46150	Agents involved in the sale of furniture, household goods, hardware and ironmongery	f
46160	Agents involved in the sale of textiles, clothing, fur, footwear and leather goods	f
46170	Agents involved in the sale of food, beverages and tobacco	f
46180	Agents specialised in the sale of other particular products	f
46190	Agents involved in the sale of a variety of goods	f
46210	Wholesale of grain, unmanufactured tobacco, seeds and animal feeds	f
46220	Wholesale of flowers and plants	f
46230	Wholesale of live animals	f
46240	Wholesale of hides, skins and leather	f
46310	Wholesale of fruit and vegetables	f
46320	Wholesale of meat and meat products	f
46330	Wholesale of dairy products, eggs and edible oils and fats	f
46341	Wholesale of fruit and vegetable juices, mineral water and soft drinks	f
46342	Wholesale of wine, beer, spirits and other alcoholic beverages	f
46350	Wholesale of tobacco products	f
46360	Wholesale of sugar and chocolate and sugar confectionery	f
46370	Wholesale of coffee, tea, cocoa and spices	f
46380	Wholesale of other food, including fish, crustaceans and molluscs	f
46390	Non-specialised wholesale of food, beverages and tobacco	f
46410	Wholesale of textiles	f
46420	Wholesale of clothing and footwear	f
46431	Wholesale of audio tapes, records, CDs and video tapes and the equipment on which these are played	f
46439	Wholesale of radio, television goods & electrical household appliances (other than records, tapes, CD's & video tapes and the equipment used for playing them)	f
46440	Wholesale of china and glassware and cleaning materials	f
46450	Wholesale of perfume and cosmetics	f
46460	Wholesale of pharmaceutical goods	f
46470	Wholesale of furniture, carpets and lighting equipment	f
46480	Wholesale of watches and jewellery	f
46491	Wholesale of musical instruments	f
46499	Wholesale of household goods (other than musical instruments) n.e.c	f
46520	Wholesale of electronic and telecommunications equipment and parts	f
46610	Wholesale of agricultural machinery, equipment and supplies	f
46620	Wholesale of machine tools	f
46630	Wholesale of mining, construction and civil engineering machinery	f
46640	Wholesale of machinery for the textile industry and of sewing and knitting machines	f
46650	Wholesale of office furniture	f
46660	Wholesale of other office machinery and equipment	f
94200	Activities of trade unions	f
46690	Wholesale of other machinery and equipment	f
46711	Wholesale of petroleum and petroleum products	f
46719	Wholesale of other fuels and related products	f
46720	Wholesale of metals and metal ores	f
46730	Wholesale of wood, construction materials and sanitary equipment	f
46740	Wholesale of hardware, plumbing and heating equipment and supplies	f
46750	Wholesale of chemical products	f
46760	Wholesale of other intermediate products	f
46770	Wholesale of waste and scrap	f
46900	Non-specialised wholesale trade	f
47110	Retail sale in non-specialised stores with food, beverages or tobacco predominating	f
47190	Other retail sale in non-specialised stores	f
47210	Retail sale of fruit and vegetables in specialised stores	f
47220	Retail sale of meat and meat products in specialised stores	f
47230	Retail sale of fish, crustaceans and molluscs in specialised stores	f
47240	Retail sale of bread, cakes, flour confectionery and sugar confectionery in specialised stores	f
47250	Retail sale of beverages in specialised stores	f
47260	Retail sale of tobacco products in specialised stores	f
47290	Other retail sale of food in specialised stores	f
47300	Retail sale of automotive fuel in specialised stores	f
47421	Retail sale of mobile telephones	f
47429	Retail sale of telecommunications equipment other than mobile telephones	f
47430	Retail sale of audio and video equipment in specialised stores	f
47510	Retail sale of textiles in specialised stores	f
47520	Retail sale of hardware, paints and glass in specialised stores	f
47530	Retail sale of carpets, rugs, wall and floor coverings in specialised stores	f
47540	Retail sale of electrical household appliances in specialised stores	f
47591	Retail sale of musical instruments and scores	f
47599	Retail of furniture, lighting, and similar (not musical instruments or scores) in specialised store	f
47610	Retail sale of books in specialised stores	f
47620	Retail sale of newspapers and stationery in specialised stores	f
47630	Retail sale of music and video recordings in specialised stores	f
47640	Retail sale of sports goods, fishing gear, camping goods, boats and bicycles	f
47650	Retail sale of games and toys in specialised stores	f
47710	Retail sale of clothing in specialised stores	f
47721	Retail sale of footwear in specialised stores	f
47722	Retail sale of leather goods in specialised stores	f
47730	Dispensing chemist in specialised stores	f
47741	Retail sale of hearing aids	f
47749	Retail sale of medical and orthopaedic goods in specialised stores (not incl. hearing aids) n.e.c.	f
47750	Retail sale of cosmetic and toilet articles in specialised stores	f
47760	Retail sale of flowers, plants, seeds, fertilizers, pet animals and pet food in specialised stores	f
47770	Retail sale of watches and jewellery in specialised stores	f
47781	Retail sale in commercial art galleries	f
47782	Retail sale by opticians	f
47789	Other retail sale of new goods in specialised stores (not commercial art galleries and opticians)	f
47791	Retail sale of antiques including antique books in stores	f
47799	Retail sale of other second-hand goods in stores (not incl. antiques)	f
47810	Retail sale via stalls and markets of food, beverages and tobacco products	f
47820	Retail sale via stalls and markets of textiles, clothing and footwear	f
47890	Retail sale via stalls and markets of other goods	f
47910	Retail sale via mail order houses or via Internet	f
47990	Other retail sale not in stores, stalls or markets	f
49100	Passenger rail transport, interurban	f
49200	Freight rail transport	f
49311	Urban and suburban passenger railway transportation by underground, metro and similar systems	f
49319	Other urban, suburban or metropolitan passenger land transport (not underground, metro or similar)	f
49320	Taxi operation	f
49390	Other passenger land transport	f
49410	Freight transport by road	f
49420	Removal services	f
49500	Transport via pipeline	f
50100	Sea and coastal passenger water transport	f
50200	Sea and coastal freight water transport	f
50300	Inland passenger water transport	f
50400	Inland freight water transport	f
51101	Scheduled passenger air transport	f
51102	Non-scheduled passenger air transport	f
51210	Freight air transport	f
51220	Space transport	f
52101	Operation of warehousing and storage facilities for water transport activities	f
52102	Operation of warehousing and storage facilities for air transport activities	f
52103	Operation of warehousing and storage facilities for land transport activities	f
52211	Operation of rail freight terminals	f
52212	Operation of rail passenger facilities at railway stations	f
52213	Operation of bus and coach passenger facilities at bus and coach stations	f
52219	Other service activities incidental to land transportation, n.e.c.	f
52220	Service activities incidental to water transportation	f
52230	Service activities incidental to air transportation	f
52241	Cargo handling for water transport activities	f
52242	Cargo handling for air transport activities	f
52243	Cargo handling for land transport activities	f
52290	Other transportation support activities	f
53100	Postal activities under universal service obligation	f
53201	Licensed carriers	f
53202	Unlicensed carriers	f
55100	Hotels and similar accommodation	f
55201	Holiday centres and villages	f
55202	Youth hostels	f
55209	Other holiday and other collective accommodation	f
55300	Recreational vehicle parks, trailer parks and camping grounds	f
55900	Other accommodation	f
56101	Licenced restaurants	f
56102	Unlicenced restaurants and cafes	f
56103	Take-away food shops and mobile food stands	f
56210	Event catering activities	f
56290	Other food services	f
56301	Licenced clubs	f
56302	Public houses and bars	f
58110	Book publishing	f
58120	Publishing of directories and mailing lists	f
58130	Publishing of newspapers	f
58141	Publishing of learned journals	f
58142	Publishing of consumer and business journals and periodicals	f
58190	Other publishing activities	f
58210	Publishing of computer games	f
59111	Motion picture production activities	f
59112	Video production activities	f
59113	Television programme production activities	f
59120	Motion picture, video and television programme post-production activities	f
59131	Motion picture distribution activities	f
59132	Video distribution activities	f
59133	Television programme distribution activities	f
59140	Motion picture projection activities	f
59200	Sound recording and music publishing activities	f
60100	Radio broadcasting	f
60200	Television programming and broadcasting activities	f
61100	Wired telecommunications activities	f
61200	Wireless telecommunications activities	f
61300	Satellite telecommunications activities	f
61900	Other telecommunications activities	f
62020	Information technology consultancy activities	f
62030	Computer facilities management activities	f
63120	Web portals	f
63910	News agency activities	f
63990	Other information service activities n.e.c.	f
64110	Central banking	f
64191	Banks	f
64192	Building societies	f
64201	Activities of agricultural holding companies	f
64202	Activities of production holding companies	f
64203	Activities of construction holding companies	f
64204	Activities of distribution holding companies	f
64205	Activities of financial services holding companies	f
64209	Activities of other holding companies n.e.c.	f
64301	Activities of investment trusts	f
64302	Activities of unit trusts	f
64303	Activities of venture and development capital companies	f
64304	Activities of open-ended investment companies	f
64305	Activities of property unit trusts	f
64306	Activities of real estate investment trusts	f
64910	Financial leasing	f
64921	Credit granting by non-deposit taking finance houses and other specialist consumer credit grantors	f
64922	Activities of mortgage finance companies	f
64929	Other credit granting n.e.c.	f
64991	Security dealing on own account	f
64992	Factoring	f
64999	Financial intermediation not elsewhere classified	f
65110	Life insurance	f
65120	Non-life insurance	f
65201	Life reinsurance	f
65202	Non-life reinsurance	f
65300	Pension funding	f
66110	Administration of financial markets	f
66120	Security and commodity contracts dealing activities	f
66190	Activities auxiliary to financial intermediation n.e.c.	f
66210	Risk and damage evaluation	f
66220	Activities of insurance agents and brokers	f
66290	Other activities auxiliary to insurance and pension funding	f
66300	Fund management activities	f
68100	Buying and selling of own real estate	f
68201	Renting and operating of Housing Association real estate	f
68202	Letting and operating of conference and exhibition centres	f
68209	Other letting and operating of own or leased real estate	f
68310	Real estate agencies	f
68320	Management of real estate on a fee or contract basis	f
69101	Barristers at law	f
69102	Solicitors	f
69109	Activities of patent and copyright agents; other legal activities n.e.c.	f
69201	Accounting and auditing activities	f
69202	Bookkeeping activities	f
69203	Tax consultancy	f
70100	Activities of head offices	f
70210	Public relations and communications activities	f
70221	Financial management	f
70229	Management consultancy activities other than financial management	f
71111	Architectural activities	f
71112	Urban planning and landscape architectural activities	f
71121	Engineering design activities for industrial process and production	f
71122	Engineering related scientific and technical consulting activities	f
71129	Other engineering activities	f
71200	Technical testing and analysis	f
72110	Research and experimental development on biotechnology	f
72190	Other research and experimental development on natural sciences and engineering	f
72200	Research and experimental development on social sciences and humanities	f
73110	Advertising agencies	f
73120	Media representation services	f
73200	Market research and public opinion polling	f
74100	specialised design activities	f
74201	Portrait photographic activities	f
74202	Other specialist photography	f
74203	Film processing	f
58290	Other software publishing	f
74209	Photographic activities not elsewhere classified	f
74300	Translation and interpretation activities	f
74901	Environmental consulting activities	f
74902	Quantity surveying activities	f
74909	Other professional, scientific and technical activities n.e.c.	f
74990	Non-trading company	f
75000	Veterinary activities	f
77110	Renting and leasing of cars and light motor vehicles	f
77120	Renting and leasing of trucks and other heavy vehicles	f
77210	Renting and leasing of recreational and sports goods	f
77220	Renting of video tapes and disks	f
77291	Renting and leasing of media entertainment equipment	f
77299	Renting and leasing of other personal and household goods	f
77310	Renting and leasing of agricultural machinery and equipment	f
77320	Renting and leasing of construction and civil engineering machinery and equipment	f
77330	Renting and leasing of office machinery and equipment (including computers)	f
77341	Renting and leasing of passenger water transport equipment	f
77342	Renting and leasing of freight water transport equipment	f
77351	Renting and leasing of air passenger transport equipment	f
77352	Renting and leasing of freight air transport equipment	f
77390	Renting and leasing of other machinery, equipment and tangible goods n.e.c.	f
77400	Leasing of intellectual property and similar products, except copyright works	f
78101	Motion picture, television and other theatrical casting activities	f
78109	Other activities of employment placement agencies	f
78200	Temporary employment agency activities	f
78300	Human resources provision and management of human resources functions	f
79110	Travel agency activities	f
79120	Tour operator activities	f
79901	Activities of tourist guides	f
79909	Other reservation service activities n.e.c.	f
80100	Private security activities	f
80200	Security systems service activities	f
80300	Investigation activities	f
81100	Combined facilities support activities	f
81210	General cleaning of buildings	f
81221	Window cleaning services	f
81222	Specialised cleaning services	f
81223	Furnace and chimney cleaning services	f
81229	Other building and industrial cleaning activities	f
81291	Disinfecting and exterminating services	f
81299	Other cleaning services	f
81300	Landscape service activities	f
82110	Combined office administrative service activities	f
82190	Photocopying, document preparation and other specialised office support activities	f
82200	Activities of call centres	f
82301	Activities of exhibition and fair organisers	f
82302	Activities of conference organisers	f
82911	Activities of collection agencies	f
82912	Activities of credit bureaus	f
82920	Packaging activities	f
82990	Other business support service activities n.e.c.	f
84110	General public administration activities	f
84120	Regulation of health care, education, cultural and other social services, not incl. social security	f
84130	Regulation of and contribution to more efficient operation of businesses	f
84210	Foreign affairs	f
84220	Defence activities	f
84230	Justice and judicial activities	f
84240	Public order and safety activities	f
84250	Fire service activities	f
84300	Compulsory social security activities	f
85100	Pre-primary education	f
85200	Primary education	f
85310	General secondary education	f
85320	Technical and vocational secondary education	f
85410	Post-secondary non-tertiary education	f
85421	First-degree level higher education	f
85422	Post-graduate level higher education	f
85510	Sports and recreation education	f
85520	Cultural education	f
85530	Driving school activities	f
85590	Other education n.e.c.	f
85600	Educational support services	f
86101	Hospital activities	f
86102	Medical nursing home activities	f
86210	General medical practice activities	f
86220	Specialists medical practice activities	f
86230	Dental practice activities	f
86900	Other human health activities	f
87100	Residential nursing care facilities	f
87200	Residential care activities for learning difficulties, mental health and substance abuse	f
87300	Residential care activities for the elderly and disabled	f
87900	Other residential care activities n.e.c.	f
88100	Social work activities without accommodation for the elderly and disabled	f
88910	Child day-care activities	f
88990	Other social work activities without accommodation n.e.c.	f
90010	Performing arts	f
90020	Support activities to performing arts	f
90030	Artistic creation	f
90040	Operation of arts facilities	f
91011	Library activities	f
91012	Archives activities	f
91020	Museums activities	f
91030	Operation of historical sites and buildings and similar visitor attractions	f
91040	Botanical and zoological gardens and nature reserves activities	f
92000	Gambling and betting activities	f
93110	Operation of sports facilities	f
93120	Activities of sport clubs	f
93130	Fitness facilities	f
93191	Activities of racehorse owners	f
93199	Other sports activities	f
93210	Activities of amusement parks and theme parks	f
93290	Other amusement and recreation activities n.e.c.	f
94110	Activities of business and employers membership organisations	f
94120	Activities of professional membership organisations	f
94910	Activities of religious organisations	f
94920	Activities of political organisations	f
94990	Activities of other membership organisations n.e.c.	f
95110	Repair of computers and peripheral equipment	f
95120	Repair of communication equipment	f
95210	Repair of consumer electronics	f
95220	Repair of household appliances and home and garden equipment	f
95230	Repair of footwear and leather goods	f
95240	Repair of furniture and home furnishings	f
95250	Repair of watches, clocks and jewellery	f
95290	Repair of personal and household goods n.e.c.	f
96010	Washing and (dry-)cleaning of textile and fur products	f
96020	Hairdressing and other beauty treatment	f
96030	Funeral and related activities	f
96040	Physical well-being activities	f
96090	Other service activities n.e.c.	f
97000	Activities of households as employers of domestic personnel	f
98000	Residents property management	f
98100	Undifferentiated goods-producing activities of private households for own use	f
98200	Undifferentiated service-producing activities of private households for own use	f
99000	Activities of extraterritorial organisations and bodies	f
99999	Dormant Company	f
46510	Wholesale of computers, computer peripheral equipment and software	f
47410	Retail sale of computers, peripheral units and software in specialised stores	f
62011	Ready-made interactive leisure and entertainment software development	f
62012	Business and domestic software development	f
62090	Other information technology service activities	f
63110	Data processing, hosting and related activities	f
\.


--
-- Name: sic sic_pkey; Type: CONSTRAINT; Schema: public; Owner: tarsier
--

ALTER TABLE ONLY public.sic
    ADD CONSTRAINT sic_pkey PRIMARY KEY (code);


--
-- PostgreSQL database dump complete
--

