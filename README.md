<h2>Why this project</h2>

<p> If you are a big fun of Vente Privé, you must know the Camp(winter/summer camp). All the products are extremly fucking cheap! (sorry for the dirty word...) 
As long as you enter into the Camp, nothing available!!! Disappointed? Don't be like that. I'd like to see your smiling face, 
you are so lovely! Be happy is the most important!!! like me:)</p>

<p> The project aims to help you to keep smiling :) and to grab interesting items in your baskets!</p>

<h2>How to use it?</h2>
<h3>Configuration file</h3>
<p>The configuration files can be found in the resource folder, named config + index.properties.</p>
<p>The index should be from 0 to n. It should be as same as the total number of threads that you want to run and don't forget to modify the TOTAL in VPMasterLauncher</p>

<ul>
<li>LOGIN:your login for vente privé</li>
<li>PASSWORD:your password</li>
<li>START_MONTH, START_DAY, START_HOUR, START_MINUIT:accurate time where the camp will take place(tips:set two min before the camp start</li>
<li>SALE_ID:id of the camp</li>
<li>MARKS:the mark you are interested in</li>
<li>SUB_MENUS:key word of sub menu of the mark, such as homme, femme etc(the more accurate you set the sub menu, the more products you get
<li>SIZE:possible sizes you want</li>
<li>SHUT_DOWN_ACTIVE:if you wanto shut down your computer automatically after camp</li>
<li>SHUT_DOWN_HOUR,SHUT_DOWN_MIN: the time you want your computer to shut down. it will be activated only if SHUT_DOWN_ACTIVE=true</li>
</ul>

<p>An example of configuration file can be found in the vpMaster/src/main/resources/config0.properties </p>
