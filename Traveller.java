public interface Traveller
{
     enum RouteType {WATER, AIR, RAIL, ROAD}
     RouteType getRouteType();//This must return the type of route the traveller uses.
}