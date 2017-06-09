package com.lovnx.entity;

import java.util.ArrayList;
import java.util.List;

public class InterfaceLimitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InterfaceLimitExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdIsNull() {
            addCriterion("interfaceId is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdIsNotNull() {
            addCriterion("interfaceId is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdEqualTo(Integer value) {
            addCriterion("interfaceId =", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdNotEqualTo(Integer value) {
            addCriterion("interfaceId <>", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdGreaterThan(Integer value) {
            addCriterion("interfaceId >", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("interfaceId >=", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdLessThan(Integer value) {
            addCriterion("interfaceId <", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdLessThanOrEqualTo(Integer value) {
            addCriterion("interfaceId <=", value, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdIn(List<Integer> values) {
            addCriterion("interfaceId in", values, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdNotIn(List<Integer> values) {
            addCriterion("interfaceId not in", values, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdBetween(Integer value1, Integer value2) {
            addCriterion("interfaceId between", value1, value2, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andInterfaceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("interfaceId not between", value1, value2, "interfaceId");
            return (Criteria) this;
        }

        public Criteria andUnitTimeIsNull() {
            addCriterion("unitTime is null");
            return (Criteria) this;
        }

        public Criteria andUnitTimeIsNotNull() {
            addCriterion("unitTime is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTimeEqualTo(Integer value) {
            addCriterion("unitTime =", value, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeNotEqualTo(Integer value) {
            addCriterion("unitTime <>", value, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeGreaterThan(Integer value) {
            addCriterion("unitTime >", value, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("unitTime >=", value, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeLessThan(Integer value) {
            addCriterion("unitTime <", value, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("unitTime <=", value, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeIn(List<Integer> values) {
            addCriterion("unitTime in", values, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeNotIn(List<Integer> values) {
            addCriterion("unitTime not in", values, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeBetween(Integer value1, Integer value2) {
            addCriterion("unitTime between", value1, value2, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("unitTime not between", value1, value2, "unitTime");
            return (Criteria) this;
        }

        public Criteria andUnitNumIsNull() {
            addCriterion("unitNum is null");
            return (Criteria) this;
        }

        public Criteria andUnitNumIsNotNull() {
            addCriterion("unitNum is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNumEqualTo(Integer value) {
            addCriterion("unitNum =", value, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumNotEqualTo(Integer value) {
            addCriterion("unitNum <>", value, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumGreaterThan(Integer value) {
            addCriterion("unitNum >", value, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("unitNum >=", value, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumLessThan(Integer value) {
            addCriterion("unitNum <", value, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumLessThanOrEqualTo(Integer value) {
            addCriterion("unitNum <=", value, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumIn(List<Integer> values) {
            addCriterion("unitNum in", values, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumNotIn(List<Integer> values) {
            addCriterion("unitNum not in", values, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumBetween(Integer value1, Integer value2) {
            addCriterion("unitNum between", value1, value2, "unitNum");
            return (Criteria) this;
        }

        public Criteria andUnitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("unitNum not between", value1, value2, "unitNum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}