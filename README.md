# CategorisationSpringBoot
 # Problématique:
Vous êtes l'ingénieur logiciel consulté par une entreprise de la place afin de résoudre une problématique de taille sur l’une de leur plateforme concernant la structuration de données autour des entités catégorie et produit.
En ce sens que la future plateforme qui devra être mise en place va accueillir la structuration hiérarchique des différentes grandes surfaces de la place : 
 AUCHAN: 2 niveaux de catégorisation
Hypermarché Exclusive: 3 niveaux de catégorisation
UTILE: 2 niveaux de catégorisation
Sakanal: 4 niveaux de catégorisation
Vous devez donc concevoir une base de donnée évolutive  , scalable et qui puisse s’adapter sans besoin d'être amendée, à toutes les grandes surfaces peu importe leur niveau de catégorisation: N niveaux avec N allant de 1 à plusieurs.
Un produit est lié à une  catégorie, et une catégorie peut être lié à 0 ou plusieurs.
La base de données requise est postgresql.
Le module doit être écrit en spring avec l’architecture mvc en intégrant les différentes interfaces de IN( saisie des données) et de OUT (affichage des données) 
en considérant les problématiques de montée en charge, proposer une logique OUT qui puisse prévenir de ce genre de problème.
en prenant en compte le fait que vous ne soyez pas immortel, mettant en place une documentation de votre code ou logique métier et vos webservices si existant.
